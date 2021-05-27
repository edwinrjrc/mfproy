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

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public void generarMenu(Integer idPersona, Integer idUsuario) throws MfServiceMenuException {
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

		List<PlatoDto> listaPlatosNoConsumidos = remoteServicePlato.platosNoConsumidos(idPersona, fechaCorte);

		int cantidadSemanas = 1;

		Date fechaHoy = new Date();
		cal.setTime(fechaHoy);

		Date fechaInicio = cal.getTime();

		cal.add(Calendar.DATE, 7 * cantidadSemanas - cal.get(Calendar.DAY_OF_WEEK));

		Date fechaFin = cal.getTime();

		menuGeneradoDto.setFechaGenerado(fechaHoy);
		menuGeneradoDto.setFechaRegistro(fechaHoy);
		menuGeneradoDto.setFechaModificacion(fechaHoy);
		menuGeneradoDto.setNumeroDias(7 * cantidadSemanas);

		log.info("platosTotal::" + listaPlatosNoConsumidos.size());

		menuGeneradoDto
				.setListaPlatos(generarDetalleMenuDto(fechaInicio, fechaFin, idUsuario, listaPlatosNoConsumidos));

		this.remoteServiceMenu.grabarMenuGenerado(menuGeneradoDto);
	}

	private List<MenuDetalleDto> generarDetalleMenuDto(Date fechaInicio, Date fechaFin, int idUsuario,
			List<PlatoDto> platosTotal) throws MfServiceMenuException {
		log.info("platos llegan::" + platosTotal.size());

		Date fechaHoy = new Date();

		Map<Integer, Integer> mapeoTipoPlato = new HashMap<Integer, Integer>();
		mapeoTipoPlato.put(1, 0);
		mapeoTipoPlato.put(2, 1);
		mapeoTipoPlato.put(3, 2);
		mapeoTipoPlato.put(4, 5);
		mapeoTipoPlato.put(5, 6);
		mapeoTipoPlato.put(6, 7);
		mapeoTipoPlato.put(7, 5);

		Calendar calendario = Calendar.getInstance();
		Date fechaConsumo = fechaInicio;
		MenuDetalleDto menuDetalleDto = null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		while (fechaConsumo.before(fechaFin) || fechaConsumo.equals(fechaFin)) {
			calendario.setTime(fechaConsumo);

			int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

			if (diaSemana != Calendar.SUNDAY) {

				int idPlatoElegido = 0;
				List<PlatoDto> platosTipoDia = new ArrayList<>();
				for (PlatoDto platoDto : platosTotal) {
					if (platoDto.getIdTipoPlato() == mapeoTipoPlato.get(diaSemana).intValue()) {
						PlatoDto dto = new PlatoDto();
						dto.setId(platoDto.getId());
						platosTipoDia.add(dto);
					}
				}

				int cantidadPlatos1 = platosTipoDia.size();
				log.info("Num platos ::" + cantidadPlatos1);
				int numeroElegido = UtilMfDto.numeroEnteroAleatorio(1, cantidadPlatos1);
				log.info("Num Elegido ::" + numeroElegido);
				idPlatoElegido = platosTipoDia.get((numeroElegido == 0 ? 1 : numeroElegido) - 1).getId();

				menuDetalleDto = new MenuDetalleDto();
				menuDetalleDto.setFechaRegistro(fechaHoy);
				menuDetalleDto.setFechaConsumo(fechaConsumo);
				menuDetalleDto.getPlatoDto().setId(idPlatoElegido);
				menuDetalleDto.setIdUsuarioRegistro(idUsuario);
				menuDetalleDto.setFechaModificacion(fechaHoy);
				menuDetalleDto.setIdUsuarioModificacion(idUsuario);
				listaMenuDetalle.add(menuDetalleDto);

			}
			calendario.add(Calendar.DATE, 1);
			fechaConsumo = calendario.getTime();
		}

		return listaMenuDetalle;
	}

	public MenuGeneradoDto consultarMenuActivo(Integer idPersona) throws MfServiceMenuException {
		MenuGeneradoDto menuDto = null;

		List<MenuGeneradoDto> salidaCabecera = remoteServiceMenu.obtenerMenuGeneradoCabecera(idPersona);
		
		Map<Integer, PlatoDto> mapPlatos = this.remoteServicePlato.consultarPlatosMap();
		
		Map<Integer,String> diaSemana = new HashMap<Integer,String>();
		diaSemana.put(Calendar.SUNDAY, "Domingo");
		diaSemana.put(Calendar.MONDAY, "Lunes");
		diaSemana.put(Calendar.TUESDAY, "Martes");
		diaSemana.put(Calendar.WEDNESDAY, "Miercoles");
		diaSemana.put(Calendar.THURSDAY, "Jueves");
		diaSemana.put(Calendar.FRIDAY, "Viernes");
		diaSemana.put(Calendar.SATURDAY, "Sabado");
		
		Calendar c = Calendar.getInstance();
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
			} else {
				throw new MfServiceMenuException("Lista con mas de 1 Menu devuelto");
			}
		}

		return menuDto;
	}
}
