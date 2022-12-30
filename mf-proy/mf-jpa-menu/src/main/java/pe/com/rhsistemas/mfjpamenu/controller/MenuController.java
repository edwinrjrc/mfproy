/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpamenu.dao.MenuDetalleRepository;
import pe.com.rhsistemas.mfjpamenu.dao.MenuRepository;
import pe.com.rhsistemas.mfjpamenu.dao.PlatoRepository;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.entity.Persona;
import pe.com.rhsistemas.mfjpamenu.entity.Plato;
import pe.com.rhsistemas.mfjpamenu.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/MenuRJPAService")
public class MenuController {

	private static final Logger log = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private PlatoRepository platoRepository;

	@Autowired
	private MenuDetalleRepository menuDetalleRepository;

	@GetMapping(value = "/ultimoMenu")
	public ResponseEntity<Map<String, Object>> consultarUltimoMenu(
			@RequestParam(name = "idPersona", required = true) Integer idPersona,
			@RequestParam(name = "fechaRango", required = true) Date fechaRango) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(idPersona, "idPersona");
		try {
			Persona persona = new Persona();
			persona.setIdPersona(Long.valueOf(idPersona));
			List<MenuDetalle> listaMenus = menuRepository.ultimosMenusRango(persona.getIdPersona(), null);
			List<MenuDetalleDto> listaMenuDto = new ArrayList<>();
			MenuDetalleDto menuDetalleDto = null;
			for (MenuDetalle menuDetalle : listaMenus) {
				menuDetalleDto = new MenuDetalleDto();
				menuDetalleDto.setFechaConsumoStr(UtilMfDto.parseDateAString(menuDetalle.getId().getFeConsumo(), Constantes.FORMAT_DATE_MAPPER));
				menuDetalleDto.setFechaConsumo(menuDetalle.getId().getFeConsumo());
				menuDetalleDto.getPlatoDto().setId(menuDetalle.getIdPlato());
				menuDetalleDto.setFechaRegistro(menuDetalle.getFeRegistro());
				menuDetalleDto.setIdUsuarioRegistro(menuDetalle.getIdUsuaCrea());
				listaMenuDto.add(menuDetalleDto);
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDto);
			status = HttpStatus.OK;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@PostMapping(value = "/menuGenerado")
	public ResponseEntity<Map<String, Object>> registrarMenu(@RequestBody MenuGeneradoDto menuGeneradoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(menuGeneradoDto, "menuGeneradoDto");

		try {
			MenuGenerado menuEntity = Utilmfjpa.parseMenuDto(menuGeneradoDto);
			MenuGenerado resp = menuRepository.saveAndFlush(menuEntity);

			List<MenuDetalle> listaMenuDetalle = new ArrayList<>();
			MenuDetalle entityDetalle = null;
			for (MenuDetalleDto dto : menuGeneradoDto.getListaPlatos()) {
				dto.setFechaConsumo(dto.getFechaConsumo());
				dto.setIdGenerado(resp.getIdGenerado());
				entityDetalle = Utilmfjpa.parseaMenuDetalleDto(dto);

				listaMenuDetalle.add(entityDetalle);
			}

			menuDetalleRepository.saveAll(listaMenuDetalle);

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@GetMapping(value = "/menuGenerado/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultaMenuGenerado(@PathVariable Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(idPersona, "idPersona");

		try {
			Persona persona = new Persona();
			persona.setIdPersona(Long.valueOf(idPersona));

			List<MenuGeneradoDto> listaMenuDto = new ArrayList<MenuGeneradoDto>();

			List<MenuGenerado> ultimoMenu = menuRepository.findByUltimoMenu(persona.getIdPersona());
			List<MenuDetalleDto> listaDetalle = null;
			for (MenuGenerado menuGenerado : ultimoMenu) {
				MenuGeneradoDto menuGeneradoDto = Utilmfjpa.parseMenuGenerado(menuGenerado);

				List<MenuDetalle> listaMenuDetalle = menuDetalleRepository.findByMenuGenerado(menuGenerado.getIdGenerado());
				listaDetalle = new ArrayList<>();
				for (MenuDetalle menuDetalle : listaMenuDetalle) {
					listaDetalle.add(Utilmfjpa.parseMenuDetalle(menuDetalle));
				}
				menuGeneradoDto.setListaPlatos(listaDetalle);

				listaMenuDto.add(menuGeneradoDto);
			}
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}
	
	
	@GetMapping(value = "/menuGenerado2/{idMenu}")
	public ResponseEntity<Map<String, Object>> consultaMenuGenerado2(@PathVariable Long idMenu) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(idMenu, "idMenu");
			
			Optional<MenuGenerado> menuGenerado = menuRepository.findById(idMenu);

			List<MenuDetalleDto> listaDetalle = null;
			
			List<MenuDetalle> listaMenuDetalle = menuDetalleRepository.findByMenuGenerado(idMenu);
			
			MenuGeneradoDto menuGeneradoDto = Utilmfjpa.parseMenuGenerado(menuGenerado.get());
			
			if (UtilMfDto.listaNoVacia(listaMenuDetalle)) {
				listaDetalle = new ArrayList<>();
				MenuDetalleDto menuDetalleDto = null;
				for (MenuDetalle menuDetalle : listaMenuDetalle) {
					
					menuDetalleDto = Utilmfjpa.parseMenuDetalle(menuDetalle);
					
					Optional<Plato> platoOptional = this.platoRepository.findById(menuDetalleDto.getPlatoDto().getId());
					menuDetalleDto.getPlatoDto().setNombrePlato(platoOptional.get().getNoPlato());
					menuDetalleDto.getPlatoDto().setDescripcionPlato(platoOptional.get().getDePlato());
					menuDetalleDto.getPlatoDto().setFechaRegistro(menuDetalleDto.getFechaRegistro());
					menuDetalleDto.getPlatoDto().setFechaModificacion(menuDetalleDto.getFechaModificacion());
					menuDetalleDto.getPlatoDto().setIdUsuarioRegistro(menuDetalleDto.getIdUsuarioRegistro());
					menuDetalleDto.getPlatoDto().setIdUsuarioModificacion(menuDetalleDto.getIdUsuarioModificacion());
					menuDetalleDto.getPlatoDto().setIdEstado(platoOptional.get().getIdEstaPlat());
					menuDetalleDto.getPlatoDto().setAcompaniamiento("S".equalsIgnoreCase(platoOptional.get().getInAcompaniamiento()));
					menuDetalleDto.getPlatoDto().getTipoCocina().setCodigo(platoOptional.get().getTipoCocina().getIdTipoCoci().toString());
					menuDetalleDto.getPlatoDto().getTipoCocina().setNombre(platoOptional.get().getTipoCocina().getDeTipoCoci());
					menuDetalleDto.getPlatoDto().getTipoCocina().setValor(platoOptional.get().getTipoCocina().getDeTipoCoci());
					menuDetalleDto.getPlatoDto().getTipoCocina().setIdUsuarioModificacion(platoOptional.get().getTipoCocina().getIdUsuaModi());
					menuDetalleDto.getPlatoDto().getTipoCocina().setIdUsuarioRegistro(platoOptional.get().getTipoCocina().getIdUsuaRegi());
					menuDetalleDto.getPlatoDto().getTipoCocina().setFechaModificacion(platoOptional.get().getTipoCocina().getFeModificacion());
					menuDetalleDto.getPlatoDto().getTipoCocina().setFechaRegistro(platoOptional.get().getTipoCocina().getFeRegistro());
					menuDetalleDto.getPlatoDto().getPersona().setId(platoOptional.get().getIdPersona());
					menuDetalleDto.getPlatoDto().setIdTipoPlato(menuDetalle.getIdTipoPlato());
					menuDetalleDto.setIdGenerado(idMenu);
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(menuDetalleDto.getFechaConsumo());
					
					menuDetalleDto.setNombreDia(UtilMfDto.nombreDia(menuDetalleDto.getFechaConsumo()));
					menuDetalleDto.setNumSemana(cal.get(Calendar.WEEK_OF_YEAR));
					
					listaDetalle.add(menuDetalleDto);
				}
				menuGeneradoDto.setListaPlatos(listaDetalle);
			}
			
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, menuGeneradoDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

}
