package pe.com.rhsistemas.mfsermenu.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoTipoPlatoDto;
import pe.com.rhsistemas.mf.cross.dto.TipoPlatoDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfsermenu.service.MenuLogicaService;
import pe.com.rhsistemas.mfsermenu.service.remote.RemoteServiceMenu;
import pe.com.rhsistemas.mfsermenu.service.remote.RemoteServicePlato;

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
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
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
		
		int cantidadSemanas = 1;

		Date fechaHoy = new Date();
		cal.setTime(fechaHoy);

		Date fechaInicio = null;

		if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY) {
			fechaCorte = UtilMfDto.parseStringADate(UtilMfDto.parseDateAString(fechaHoy, "dd/MM/yyyy") + " 11:00:00 am",
					"dd/MM/yyyy HH:mm:ss aaa",null);
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

		log.debug("platosTotal::" + listaPlatosNoConsumidos.size());

		menuGeneradoDto
				.setListaPlatos(generarDetalleMenuDto(fechaInicio, fechaFin, idUsuario, null, listaPlatosNoConsumidos, listaTiposPlato));

		this.remoteServiceMenu.grabarMenuGenerado(menuGeneradoDto);
	}

	private List<MenuDetalleDto> generarDetalleMenuDto(Date fechaInicio, Date fechaFin, int idUsuario, Integer[] tiposConsumidos,
			List<PlatoDto> platosTotal, List<PlatoTipoPlatoDto> listaTiposPlatos) throws MfServiceMenuException, UtilMfDtoException {
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
		
		log.debug("mapeoTipoPlato ::"+mapeoTipoPlato.toString());

		Calendar calendario = Calendar.getInstance();
		MenuDetalleDto menuDetalleDto = null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		log.debug("fecha inicio ::" + UtilMfDto.parseDateAString(fechaInicio, null));
		log.debug("fecha fin ::" + UtilMfDto.parseDateAString(fechaFin, null));
		List<PlatoTipoPlatoDto> indiceTiposEliminar = null;
		calendario.setTime(fechaInicio);
		while (calendario.getTime().before(fechaFin) || calendario.getTime().equals(fechaFin)) {
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
				int cantidadPlatos1 = platosTipoDia.size();
				if (cantidadPlatos1 != 0) {
					int numeroElegido = UtilMfDto.numeroEnteroAleatorio(1, cantidadPlatos1);
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
					
					// Elimina los platos de acuerdo al indice
					listaTiposPlatos.removeAll(indiceTiposEliminar);
					
					// Limpiamos la lista de indices de tipos de platos a eliminar
					indiceTiposEliminar = null;

					menuDetalleDto = new MenuDetalleDto();
					menuDetalleDto.setFechaRegistro(fechaHoy);
					String feConsumo = UtilMfDto.parseDateAString(calendario.getTime(),null);
					feConsumo = feConsumo + " 01:00:00 PM";
					menuDetalleDto.setFechaConsumo(UtilMfDto.parseStringADate(feConsumo, "dd/MM/yyyy hh:mm:ss a",null));
					menuDetalleDto.getPlatoDto().setId(idPlatoElegido);
					menuDetalleDto.setIdTipoPlato(mapeoTipoPlato.get(diaSemana).intValue());
					menuDetalleDto.setIdUsuarioRegistro(idUsuario);
					menuDetalleDto.setFechaModificacion(fechaHoy);
					menuDetalleDto.setIdUsuarioModificacion(idUsuario);
					log.info("fechaConsumo ::"+menuDetalleDto.getFechaConsumo().toString());
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
		List<MenuDetalleDto> listaDetalle2 = new ArrayList<>();
		if (UtilMfDto.listaNoVacia(salidaCabecera)) {
			if (salidaCabecera.size() > 0) {
				menuDto = salidaCabecera.get(0);

				if (UtilMfDto.listaNoVacia(menuDto.getListaPlatos())) {
					Map<Integer, MenuDetalleDto> detallePlatos = remoteServiceMenu
							.obtenerMenuGeneradoDetalle(Long.valueOf(menuDto.getIdGenerado()).intValue());
					
					List<PlatoFavoritoDto> lista = obtenerPlatosFavoritos(new ArrayList<MenuDetalleDto>(detallePlatos.values()), menuDto.getIdPersona());
					
					Iterator<Entry<Integer, MenuDetalleDto>> listaDetalle = detallePlatos.entrySet().iterator();

					while (listaDetalle.hasNext()) {
						Entry<Integer, MenuDetalleDto> entryMenuDetalle = listaDetalle.next();
						MenuDetalleDto menuDetalleDto = entryMenuDetalle.getValue();
						
						boolean favorito = false;
						
						menuDetalleDto.setPlatoDto(mapPlatos.get(menuDetalleDto.getPlatoDto().getId()));
						if (UtilMfDto.listaNoVacia(lista)) {
							favorito = false;
							for (PlatoFavoritoDto favoritoDto : lista) {
								if (menuDetalleDto.getPlatoDto().getId().equals(favoritoDto.getIdPlato())) {
									favorito = true;
									break;
								}
							}
						}
						menuDetalleDto.getPlatoDto().setFavorito(favorito);
						c.setTime(menuDetalleDto.getFechaConsumo());
						menuDetalleDto.setNumSemana(c.get(Calendar.WEEK_OF_YEAR));
						menuDetalleDto.setNombreDia(diaSemana.get(c.get(Calendar.DAY_OF_WEEK)));
						listaDetalle2.add(menuDetalleDto);
					}

					menuDto.setDetalleMenu(listaDetalle);
					menuDto.setListaPlatos(listaDetalle2);
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
	
	private List<PlatoFavoritoDto> obtenerPlatosFavoritos(List<MenuDetalleDto> listPlatos, Integer idPersona) throws MfServiceMenuException {
		String listaPlatos = "";
		if (UtilMfDto.listaNoVacia(listPlatos)) {
			for (MenuDetalleDto detaDto : listPlatos) {
				listaPlatos = listaPlatos + detaDto.getPlatoDto().getId() + ",";
			}
		}
		listaPlatos = StringUtils.substring(listaPlatos, 0, StringUtils.length(listaPlatos)-1);
		
		return this.remoteServicePlato.consultarPlatoFavorito(listaPlatos, idPersona);
	}

	public void cambiarPlatoDia(Integer idPersona, Integer idTipoPlato, Integer numeroDia) throws MfServiceMenuException {
		List<MenuGeneradoDto> menus = this.remoteServiceMenu.ultimoMenu(idPersona);
		MenuGeneradoDto ultimoMenuGenerado = null;
		if (menus != null && !menus.isEmpty()) {
			ultimoMenuGenerado = menus.get(0);
		}
		
		Map<Integer, MenuDetalleDto> listaDetalle = this.remoteServiceMenu.obtenerMenuGeneradoDetalle(Integer.valueOf(String.valueOf(ultimoMenuGenerado.getIdGenerado())));
		
		MenuDetalleDto menuDia = listaDetalle.get(numeroDia);
		
		Date fechaCorte = null;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		fechaCorte = cal.getTime();
		
		Date fechaHasta = new Date();
		cal.setTime(fechaHasta);
		cal.add(Calendar.DATE, -1);

		List<PlatoDto> listaPlatosNoConsumidos = remoteServicePlato.platosNoConsumidosTipo(idPersona, fechaCorte,
				cal.getTime(), menuDia.getIdTipoPlato());
		
		int numeroElegido = UtilMfDto.numeroEnteroAleatorio(1, listaPlatosNoConsumidos.size());
		log.debug("Num Elegido ::" + numeroElegido);
		Integer idPlatoElegido = listaPlatosNoConsumidos.get((numeroElegido == 0 ? 1 : numeroElegido) - 1).getId();
		
		menuDia.getPlatoDto().setId(idPlatoElegido);
		menuDia.setIdGenerado(Long.valueOf(ultimoMenuGenerado.getIdGenerado()).intValue());
		
		log.info("fecha consumo :: "+menuDia.getFechaConsumo());
		
		this.remoteServiceMenu.grabarMenuDetalleDia(menuDia);
	}

	private Integer aleatorioTipos(List<Integer> listaEscogidos) {
		log.debug("tamanio :: "+tipoPlatos.size());
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
	
	private void inicializarTiposPlatos() throws MfServiceMenuException {
		List<TipoPlatoDto> listaTiposPlatos = this.remoteServicePlato.consultarTiposPlatoPlatos();
		if (UtilMfDto.listaNoVacia(listaTiposPlatos)) {
			tipoPlatos = new ArrayList<Integer>();
			for (TipoPlatoDto tipoPlatoDto : listaTiposPlatos) {
				tipoPlatos.add(tipoPlatoDto.getIdTipo().intValue());
			}
		}
	}
	
}
