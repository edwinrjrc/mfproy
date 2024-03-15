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

import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
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
import pe.com.rhsistemas.mfsermenu.service.remote.RemoteServiceConfiguracionFamilia;
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
	@Autowired
	private RemoteServiceConfiguracionFamilia remoteServiceConfiguracionFamilia;
	
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

		log.debug("Recibiendo parametros");
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

		List<PlatoDto> listaPlatosNoConsumidos = remoteServicePlato.platosNoConsumidos(idPersona, fechaCorte,cal.getTime());
		
		// Consulta de platos no consumidos
		List<PlatoTipoPlatoDto> listaTiposPlato = remoteServicePlato.consultarTipoPlatoxPlato(idPersona,fechaCorte,cal.getTime());
		
		int cantidadSemanas = 1;

		Date fechaHoy = new Date();
		cal.setTime(fechaHoy);
		
		int diaHoy = cal.get(Calendar.DAY_OF_WEEK);
		int diaHoy2 = diaHoy;
		
		Map<Integer,Date> diasSemana = new HashMap<Integer,Date>();
		log.debug(diaHoy2+"");
		while (diaHoy2 <= Calendar.SATURDAY) {
			diasSemana.put(diaHoy2, cal.getTime());
			cal.add(Calendar.DATE, 1);
			log.debug(cal.toString());
			diaHoy2++;
		}
		Date fechaInicio = null;
		
		ConfiguracionCuentaDto configuracion = remoteServiceConfiguracionFamilia.consultarConfiguracionCuenta(idPersona);
		String[] diasCocina = null;
		
		List<Date> diasCocinarFinal = new ArrayList<>();
		
		if (configuracion != null) {
			diasCocina = StringUtils.split(configuracion.getDiasCocinaSemana(), ",");
			for (String diaString : diasCocina) {
				if (StringUtils.isNotBlank(diaString)) {
					Date fechaD = diasSemana.get(UtilMfDto.parseStringAInteger(diaString));
					if (fechaD != null) {
						diasCocinarFinal.add(fechaD);
					}
				}
			}
		}
		else {
			diasCocinarFinal.addAll(diasSemana.values());
		}
		/*else {
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
		}*/

		fechaInicio = cal.getTime();
		cal.add(Calendar.DATE, 7 * cantidadSemanas - cal.get(Calendar.DAY_OF_WEEK));
		Date fechaFin = cal.getTime();
		
		int numeroDias = 0;
		//numeroDias = diasCocina.length;
		numeroDias = diasCocinarFinal.size();
		
		menuGeneradoDto.setFechaGenerado(fechaHoy);
		menuGeneradoDto.setFechaRegistro(UtilMfDto.parseDateASqlTimestamp(fechaHoy));
		menuGeneradoDto.setFechaModificacion(UtilMfDto.parseDateASqlTimestamp(fechaHoy));
		menuGeneradoDto.setNumeroDias(numeroDias * cantidadSemanas);
		menuGeneradoDto.setFechaDesde(fechaInicio);
		menuGeneradoDto.setFechaHasta(fechaFin);

		/*
		 * Valida si ultimo menu generado esta activo
		 */
		if (ultimoMenuGenerado != null) {
			if (ultimoMenuGenerado.getFechaGenerado().after(fechaInicio) && ultimoMenuGenerado.getFechaGenerado().before(fechaFin)) {
				menuGeneradoDto.setIdGenerado(ultimoMenuGenerado.getIdGenerado());
			}
		}
		log.debug("platosTotal::" + listaPlatosNoConsumidos.size());

		menuGeneradoDto
				.setListaPlatos(generarDetalleMenuDto(diasCocinarFinal, idUsuario, null, listaPlatosNoConsumidos, listaTiposPlato));

		this.remoteServiceMenu.grabarMenuGenerado(menuGeneradoDto);
	}

	private List<MenuDetalleDto> generarDetalleMenuDto(List<Date> diasCocinar, int idUsuario, Integer[] tiposConsumidos,
			List<PlatoDto> platosTotal, List<PlatoTipoPlatoDto> listaTiposPlatos) throws MfServiceMenuException, UtilMfDtoException {
		inicializarTiposPlatos();
		
		Date fechaHoy = new Date();
		
		log.debug(diasCocinar.toString());
		
		Map<Integer, Integer> mapeoTipoPlato = new HashMap<Integer, Integer>();
		Calendar ca = Calendar.getInstance();
		MenuDetalleDto menuDetalleDto = null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		//List<PlatoTipoPlatoDto> indiceTiposEliminar = null;
		List<PlatoTipoPlatoDto> platosTipoDia = new ArrayList<>();
		for (Date dateCocinar : diasCocinar) {
			
			ca.setTime(dateCocinar);
			int a = aleatorioTipos();
			mapeoTipoPlato.put(ca.get(Calendar.DAY_OF_WEEK), a);
			
			int idPlatoElegido = 0;
			platosTipoDia.clear();
			for (PlatoTipoPlatoDto ptpDto : listaTiposPlatos) {
				if (ptpDto.getIdTipoPlato().intValue() == a) {
					platosTipoDia.add(ptpDto);
				}
			}
			int cantidadPlatos1 = platosTipoDia.size();
			if (cantidadPlatos1 != 0) {
				int numeroElegido = UtilMfDto.numeroEnteroAleatorio(1, cantidadPlatos1);
				idPlatoElegido = platosTipoDia.get((numeroElegido == 0 ? 1 : numeroElegido) - 1).getIdPlato().intValue();
				
				/*List<Integer> tiposPlatoElegido = new ArrayList<>();
				for (PlatoTipoPlatoDto ptpDto : listaTiposPlatos) {
					if (ptpDto.getIdPlato().intValue() == idPlatoElegido) {
						tiposPlatoElegido.add(ptpDto.getIdTipoPlato());
						break;
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
				}*/
				
				// Elimina los platos de acuerdo al indice
				//listaTiposPlatos.removeAll(indiceTiposEliminar);
				
				// Limpiamos la lista de indices de tipos de platos a eliminar
				//indiceTiposEliminar = null;

				menuDetalleDto = new MenuDetalleDto();
				menuDetalleDto.setFechaRegistro(UtilMfDto.parseDateASqlTimestamp(fechaHoy));
				String feConsumo = UtilMfDto.parseDateAString(dateCocinar,null);
				feConsumo = feConsumo + " 01:00:00 PM";
				menuDetalleDto.setFechaConsumo(UtilMfDto.parseStringADate(feConsumo, "dd/MM/yyyy hh:mm:ss a",null));
				menuDetalleDto.getPlatoDto().setId(idPlatoElegido);
				menuDetalleDto.setIdTipoPlato(mapeoTipoPlato.get(ca.get(Calendar.DAY_OF_WEEK)).intValue());
				menuDetalleDto.setIdUsuarioRegistro(idUsuario);
				menuDetalleDto.setFechaModificacion(UtilMfDto.parseDateASqlTimestamp(fechaHoy));
				menuDetalleDto.setIdUsuarioModificacion(idUsuario);
				log.debug("fechaConsumo ::"+menuDetalleDto.getFechaConsumo().toString());
				listaMenuDetalle.add(menuDetalleDto);
			}
			platosTipoDia.clear();
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
						
						menuDetalleDto.setIdGenerado(Long.valueOf(menuDto.getIdGenerado()));
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
		Integer idPlatoElegido = listaPlatosNoConsumidos.get((numeroElegido == 0 ? 1 : numeroElegido) - 1).getId();
		
		menuDia.getPlatoDto().setId(idPlatoElegido);
		menuDia.setIdGenerado(ultimoMenuGenerado.getIdGenerado());
		
		this.remoteServiceMenu.grabarMenuDetalleDia(menuDia);
	}
	
	public void cambiarPlatoMenuDia(Long idMenu, Date fechaConsumo, Long idPlato) throws MfServiceMenuException {
		MenuDetalleDto menuDetaDto = new MenuDetalleDto();
		menuDetaDto.setFechaConsumo(fechaConsumo);
		menuDetaDto.setIdGenerado(idMenu);
		menuDetaDto.getPlatoDto().setId(idPlato.intValue());
		this.remoteServiceMenu.cambiarPlatoMenuDia(menuDetaDto);
	}

	/**
	 * @see Obtener el tipo de plato de forma aleatoria
	 * @return
	 */
	private Integer aleatorioTipos() {
		int nuAleatorio = UtilMfDto.numeroEnteroAleatorio(1, tipoPlatos.size()) - 1;
		
		int nuTipo = tipoPlatos.get(nuAleatorio);
		
		tipoPlatos.remove(nuAleatorio);
		
		return nuTipo;
	}
	
	/**
	 * @see Limpiar y cargar de nuevo la lista de los tipos de plato
	 * @throws MfServiceMenuException
	 */
	private void inicializarTiposPlatos() throws MfServiceMenuException {
		// Consulta los tipos de plato que son de fondo
		List<TipoPlatoDto> listaTiposPlatos = this.remoteServicePlato.consultarTiposPlatoPlatos();
		if (UtilMfDto.listaNoVacia(listaTiposPlatos)) {
			tipoPlatos = new ArrayList<Integer>();
			for (TipoPlatoDto tipoPlatoDto : listaTiposPlatos) {
				tipoPlatos.add(tipoPlatoDto.getIdTipo().intValue());
			}
		}
	}
	
}
