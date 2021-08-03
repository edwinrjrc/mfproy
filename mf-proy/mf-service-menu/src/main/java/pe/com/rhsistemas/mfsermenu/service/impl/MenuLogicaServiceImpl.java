package pe.com.rhsistemas.mfsermenu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoTipoPlatoDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfsermenu.service.MenuLogicaService;

/**
 * @author Edwin
 *
 */
@Service
public class MenuLogicaServiceImpl implements MenuLogicaService {

	private static final Logger log = LoggerFactory.getLogger(MenuLogicaServiceImpl.class);

	@Autowired
	private RemoteServicePlato remoteServicePlato;
	@Autowired
	private RemoteServiceMenu remoteServiceMenu;
	
	List<Integer> tipoPlatos = null;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public void generarMenu(Integer idPersona, Integer idUsuario) throws MfServiceMenuException, UtilMfDtoException {
		MenuGeneradoDto menuGeneradoDto = null;

		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(idPersona, "idPersona");
		UtilMfDto.pintaLog(idUsuario, "idUsuario");

		menuGeneradoDto = new MenuGeneradoDto();
		menuGeneradoDto.setIdPersona(idPersona);
		menuGeneradoDto.setIdUsuarioRegistro(idUsuario);
		menuGeneradoDto.setIdUsuarioModificacion(idUsuario);

		Date fechaCorte = null;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		fechaCorte = cal.getTime();
		
		/*
		 * Consulta de ultimo menu generado
		 */
		List<MenuGeneradoDto> menus = this.remoteServiceMenu.ultimoMenu(idPersona);
		MenuGeneradoDto ultimoMenuGenerado = null;
		if (menus != null && !menus.isEmpty()) {
			ultimoMenuGenerado = menus.get(0);
		}

		Date fechaHasta = new Date();
		cal.setTime(fechaHasta);
		cal.add(Calendar.DATE, -1);

		List<PlatoDto> listaPlatosNoConsumidos = remoteServicePlato.platosNoConsumidos(idPersona, fechaCorte,
				cal.getTime());
		
		List<PlatoTipoPlatoDto> listaTiposPlato = remoteServicePlato.consultarTipoPlatoxPlato(idPersona,fechaCorte,cal.getTime());
		
		log.info("lista tipoPlato");

		int cantidadSemanas = 1;

		Date fechaHoy = new Date();
		cal.setTime(fechaHoy);

		Date fechaInicio = null;

		if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
			fechaCorte = UtilMfDto.parseStringADate(UtilMfDto.parseDateAString(fechaHoy, "dd/MM/yyyy") + " 11:00:00 am",
					"dd/MM/yyyy HH:mm:ss aaa");
			fechaInicio = cal.getTime();
			if (fechaHoy.after(fechaCorte)) {
				cal.add(Calendar.DATE, 1);
				fechaInicio = cal.getTime();
			}
		} else {
			cal.setTime(fechaHoy);
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				cal.add(Calendar.DATE, 1);
			} else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				cal.add(Calendar.DATE, 2);
			}
			fechaInicio = cal.getTime();
		}

		fechaInicio = cal.getTime();

		cal.add(Calendar.DATE, 7 * cantidadSemanas - cal.get(Calendar.DAY_OF_WEEK));

		Date fechaFin = cal.getTime();

		menuGeneradoDto.setFechaGenerado(fechaHoy);
		menuGeneradoDto.setFechaRegistro(fechaHoy);
		menuGeneradoDto.setFechaModificacion(fechaHoy);
		menuGeneradoDto.setNumeroDias(7 * cantidadSemanas);
		menuGeneradoDto.setFechaDesde(fechaInicio);
		menuGeneradoDto.setFechaHasta(fechaFin);

		/*
		 * Valida si ultimo menu generado esta activo
		 */
		if (ultimoMenuGenerado != null) {
			if ((ultimoMenuGenerado.getFechaGenerado().after(fechaInicio)
					&& ultimoMenuGenerado.getFechaGenerado().before(fechaFin))
					|| (ultimoMenuGenerado.getFechaGenerado().after(fechaInicio)
							&& ultimoMenuGenerado.getFechaGenerado().before(fechaFin))) {
				menuGeneradoDto.setIdGenerado(ultimoMenuGenerado.getIdGenerado());
			}
		}

		log.info("platosTotal::" + listaPlatosNoConsumidos.size());

		menuGeneradoDto
				.setListaPlatos(generarDetalleMenuDto(fechaInicio, fechaFin, idUsuario, null, listaPlatosNoConsumidos, listaTiposPlato));

		this.remoteServiceMenu.grabarMenuGenerado(menuGeneradoDto);
	}

	private List<MenuDetalleDto> generarDetalleMenuDto(Date fechaInicio, Date fechaFin, int idUsuario, Integer[] tiposConsumidos,
			List<PlatoDto> platosTotal, List<PlatoTipoPlatoDto> listaTiposPlatos) throws MfServiceMenuException, UtilMfDtoException {
		log.info("platos llegan::" + platosTotal.size());
		inicializarTiposPlatos();
		
		Date fechaHoy = new Date();
		
		// Seleccion de tipos de plato por dia
		List<Integer> listaEscogidos = new ArrayList<>();
		
		Map<Integer, Integer> mapeoTipoPlato = new HashMap<Integer, Integer>();
		mapeoTipoPlato.put(Calendar.SUNDAY, 0);
		
		int a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.MONDAY, a);
		
		a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.TUESDAY, a);
		
		a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.WEDNESDAY, a);
		
		a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.THURSDAY, a);
		
		a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.FRIDAY, a);
		
		a = aleatorioTipos(listaEscogidos);
		listaEscogidos.add(a);
		
		mapeoTipoPlato.put(Calendar.SATURDAY, a);
		
		log.info("mapeoTipoPlato ::"+mapeoTipoPlato.toString());

		Calendar calendario = Calendar.getInstance();
		MenuDetalleDto menuDetalleDto = null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		log.info("fecha inicio ::" + UtilMfDto.parseDateAString(fechaInicio, null));
		log.info("fecha fin ::" + UtilMfDto.parseDateAString(fechaFin, null));
		List<PlatoTipoPlatoDto> indiceTiposEliminar = null;
		calendario.setTime(fechaInicio);
		while (calendario.getTime().before(fechaFin) || calendario.getTime().equals(fechaFin)) {
			log.info("fechaConsumo ::" + UtilMfDto.parseDateAString(calendario.getTime(), null));
			int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

			if (diaSemana != Calendar.SUNDAY) {
				int idPlatoElegido = 0;
				List<PlatoTipoPlatoDto> platosTipoDia = new ArrayList<>();
				String b = "";
				for (PlatoTipoPlatoDto ptpDto : listaTiposPlatos) {
					b = b + ptpDto.getIdPlato() + ",";
					if (ptpDto.getIdTipoPlato().intValue() == mapeoTipoPlato.get(diaSemana).intValue()) {
						PlatoTipoPlatoDto dto = new PlatoTipoPlatoDto();
						dto.setIdPlato(ptpDto.getIdPlato());
						dto.setIdTipoPlato(ptpDto.getIdTipoPlato());
						platosTipoDia.add(dto);
					}
				}
				log.info("id tipo plato ::"+mapeoTipoPlato.get(diaSemana).intValue());
				log.info("id plato ::"+b);
				int cantidadPlatos1 = platosTipoDia.size();
				if (cantidadPlatos1 != 0) {
					log.info("Num platos ::" + cantidadPlatos1);
					int numeroElegido = UtilMfDto.numeroEnteroAleatorio(1, cantidadPlatos1);
					log.info("Num Elegido ::" + numeroElegido);
					idPlatoElegido = platosTipoDia.get((numeroElegido == 0 ? 1 : numeroElegido) - 1).getIdPlato();
					
					List<Integer> tiposPlatoElegido = new ArrayList<>();
					for (PlatoTipoPlatoDto ptpDto : listaTiposPlatos) {
						if (ptpDto.getIdPlato().intValue() == idPlatoElegido) {
							tiposPlatoElegido.add(ptpDto.getIdTipoPlato());
						}
					}
					
					indiceTiposEliminar = new ArrayList<PlatoTipoPlatoDto>();
					// Buscar los indices de los tipos de plato a eliminar
					for (int i=0; i<listaTiposPlatos.size(); i++) {
						PlatoTipoPlatoDto tipo = listaTiposPlatos.get(i);
						for (Integer tipoElegido : tiposPlatoElegido) {
							if (tipo.getIdTipoPlato().intValue() == tipoElegido.intValue()) {
								indiceTiposEliminar.add(tipo);
							}
						}
					}
					
					log.info("Antes de Eliminar ::" + listaTiposPlatos.size());
					log.info("Voy a Eliminar ::" + indiceTiposEliminar.size());
					// Elimina los platos de acuerdo al indice
					listaTiposPlatos.removeAll(indiceTiposEliminar);
					log.info("me quedan ::" + listaTiposPlatos.size());
					
					// Limpiamos la lista de indices de tipos de platos a eliminar
					indiceTiposEliminar = null;

					menuDetalleDto = new MenuDetalleDto();
					menuDetalleDto.setFechaRegistro(fechaHoy);
					String feConsumo = UtilMfDto.parseDateAString(calendario.getTime(),null);
					feConsumo = feConsumo + " 01:00:00 PM";
					menuDetalleDto.setFechaConsumo(UtilMfDto.parseStringADate(feConsumo, "dd/MM/yyyy hh:mm:ss a"));
					menuDetalleDto.getPlatoDto().setId(idPlatoElegido);
					menuDetalleDto.setIdUsuarioRegistro(idUsuario);
					menuDetalleDto.setFechaModificacion(fechaHoy);
					menuDetalleDto.setIdUsuarioModificacion(idUsuario);
					listaMenuDetalle.add(menuDetalleDto);
				}
			}
			calendario.add(Calendar.DATE, 1);
		}

		return listaMenuDetalle;
	}

	public List<Map<String, Object>> consultarMenuActivo(Integer idPersona) throws MfServiceMenuException {
		MenuGeneradoDto menuDto = null;

		List<MenuGeneradoDto> salidaCabecera = remoteServiceMenu.obtenerMenuGeneradoCabecera(idPersona);

		Map<Integer, PlatoDto> mapPlatos = this.remoteServicePlato.consultarPlatosMap();

		Map<Integer, String> diaSemana = new HashMap<Integer, String>();
		diaSemana.put(Calendar.SUNDAY, "Domingo");
		diaSemana.put(Calendar.MONDAY, "Lunes");
		diaSemana.put(Calendar.TUESDAY, "Martes");
		diaSemana.put(Calendar.WEDNESDAY, "Miercoles");
		diaSemana.put(Calendar.THURSDAY, "Jueves");
		diaSemana.put(Calendar.FRIDAY, "Viernes");
		diaSemana.put(Calendar.SATURDAY, "Sabado");

		Calendar c = Calendar.getInstance();
		Map<String, Object> semanaMap = null;
		List<Map<String, Object>> listaSemana = new ArrayList<>();
		if (UtilMfDto.listaNoVacia(salidaCabecera)) {
			if (salidaCabecera.size() > 0) {
				menuDto = salidaCabecera.get(0);

				if (UtilMfDto.listaNoVacia(menuDto.getListaPlatos())) {
					List<MenuDetalleDto> listaDetalle = remoteServiceMenu
							.obtenerMenuGeneradoDetalle(Long.valueOf(menuDto.getIdGenerado()).intValue());

					for (MenuDetalleDto menuDetalleDto : listaDetalle) {
						menuDetalleDto.setPlatoDto(mapPlatos.get(menuDetalleDto.getPlatoDto().getId()));
						c.setTime(menuDetalleDto.getFechaConsumo());
						menuDetalleDto.setNumSemana(c.get(Calendar.WEEK_OF_YEAR));
						menuDetalleDto.setNombreDia(diaSemana.get(c.get(Calendar.DAY_OF_WEEK)));
					}

					menuDto.setListaPlatos(listaDetalle);
				}

				semanaMap = new HashMap<String, Object>();
				semanaMap.put("menuSemana", menuDto);
				listaSemana.add(semanaMap);
			} else {
				throw new MfServiceMenuException("Lista con mas de 1 Menu devuelto");
			}
		}

		return listaSemana;
	}

	private Integer aleatorioTipos(List<Integer> listaEscogidos) {
		log.info("tamanio :: "+tipoPlatos.size());
		for (Integer iplato2 : listaEscogidos) {
			for (Integer iplato : tipoPlatos) {
				if (iplato.intValue() == iplato2.intValue()) {
					tipoPlatos.remove(iplato);
					break;
				}
			}
		}

		return tipoPlatos.get(UtilMfDto.numeroEnteroAleatorio(1, tipoPlatos.size()) - 1);
	}
	
	private void inicializarTiposPlatos() {
		tipoPlatos = new ArrayList<Integer>();
		tipoPlatos.add(1);
		tipoPlatos.add(2);
		tipoPlatos.add(3);
		tipoPlatos.add(4);
		tipoPlatos.add(5);
		tipoPlatos.add(6);
		tipoPlatos.add(7);
		tipoPlatos.add(11);
	}
	
}
