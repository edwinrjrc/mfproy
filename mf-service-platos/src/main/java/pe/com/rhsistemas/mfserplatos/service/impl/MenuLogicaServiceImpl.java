package pe.com.rhsistemas.mfserplatos.service.impl;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMf;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfserplatos.service.MenuLogicaService;


/**
 * @author Edwin
 *
 */
@Service
public class MenuLogicaServiceImpl implements MenuLogicaService{
	
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
	
	public void generarMenu(Integer idPersona, Integer idUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		try {
			MenuGeneradoDto menuGeneradoDto = new MenuGeneradoDto();
			menuGeneradoDto.setIdPersona(idPersona);
			menuGeneradoDto.setIdUsuarioRegistro(idUsuario);
			menuGeneradoDto.setIdUsuarioModificacion(idUsuario);
			
			List<MenuDetalleDto> listaMenu = remoteServiceMenu.ultimoMenu(idPersona);
			UtilMf.pintaLog(listaMenu, "listaMenu");
			
			int cantidadSemanas = 1;
			
			Date fechaHoy = new Date();
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(fechaHoy);
			
			Date fechaInicio = calendario.getTime();
			
			calendario.add(Calendar.DATE, 7*cantidadSemanas - calendario.get(Calendar.DAY_OF_WEEK) );
			
			Date fechaFin = calendario.getTime();
			
			menuGeneradoDto.setFechaRegistro(fechaHoy);
			menuGeneradoDto.setFechaModificacion(fechaHoy);
			menuGeneradoDto.setNumeroDias(7 * cantidadSemanas);
			
			List<PlatoDto> platosTotal = remoteServicePlato.consultarPlatos();
			
			List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
			if (listaMenu != null && !listaMenu.isEmpty()) {
				List<PlatoDto> platos2 = new ArrayList<>();
				if (platosTotal != null && !platosTotal.isEmpty()) {
					for (PlatoDto dto : platosTotal) {
						boolean yaEsta = false;
						for (MenuDetalleDto detalleDto : listaMenu) {
							if (dto.getId() == detalleDto.getIdPlato()) {
								yaEsta = true;
								break;
							}
						}
						if (!yaEsta) {
							platos2.add(dto);
						}
					}
				}
				generarDetalleMenuDto(fechaInicio, fechaFin, idUsuario, platos2);
			}
			else {
				
				generarDetalleMenuDto(fechaInicio, fechaFin, idUsuario, platosTotal);
			}
			menuGeneradoDto.setListaPlatos(listaMenuDetalle);
		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private List<MenuDetalleDto> generarDetalleMenuDto(Date fechaInicio, Date fechaFin, int idUsuario, List<PlatoDto> platosTotal) throws MfServiceMenuException {
		Date fechaHoy = new Date();
		
		Map<Integer,Integer> mapeoTipoPlato = new HashMap<Integer,Integer>();
		mapeoTipoPlato.put(1, 0);
		mapeoTipoPlato.put(2, 1);
		mapeoTipoPlato.put(3, 2);
		mapeoTipoPlato.put(4, 5);
		mapeoTipoPlato.put(5, 6);
		mapeoTipoPlato.put(6, 7);
		mapeoTipoPlato.put(7, 5);
		
		Calendar calendario = Calendar.getInstance();
		Date fechaConsumo = fechaInicio;
		MenuDetalleDto menuDetalleDto =  null;
		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
		while (fechaConsumo.before(fechaFin) || fechaConsumo.equals(fechaFin)) {
			calendario.setTime(fechaConsumo);
			
			int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
			
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
			int numeroElegido = UtilMf.numeroEnteroAleatorio(1, cantidadPlatos1);
			idPlatoElegido = platosTipoDia.get(numeroElegido - 1).getId();
			
			menuDetalleDto = new MenuDetalleDto();
			menuDetalleDto.setFechaRegistro(fechaHoy);
			menuDetalleDto.setFechaConsumo(fechaConsumo);
			menuDetalleDto.setIdPlato(idPlatoElegido);
			menuDetalleDto.setIdUsuarioRegistro(idUsuario);
			menuDetalleDto.setFechaModificacion(fechaHoy);
			menuDetalleDto.setIdUsuarioModificacion(idUsuario);
			listaMenuDetalle.add(menuDetalleDto);
			
			calendario.add(Calendar.DATE, 1);
			fechaConsumo = calendario.getTime();
		}
		
		return listaMenuDetalle;
	}
}
