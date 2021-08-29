/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpamenu.dao.MenuDetalleRepository;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/MenuDetalleRJPAService")
public class MenuDetalleController {

	private static final Logger log = LoggerFactory.getLogger(MenuDetalleController.class);

	@Autowired
	private MenuDetalleRepository menuDetalleRepository;

	@GetMapping(value = "/menuDetalle/{idMenuGenerado}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> consultarDetalleMenu(@PathVariable Long idMenuGenerado) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(idMenuGenerado, "idMenuGenerado");
		
		try {
			status = HttpStatus.NO_CONTENT;
			
			MenuGenerado menuGenerado = new MenuGenerado();
			menuGenerado.setIdGenerado(idMenuGenerado);
			
			List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();
			
			List<MenuDetalle> listaDetalle = menuDetalleRepository.findByMenuGenerado(menuGenerado.getIdGenerado());
			for (MenuDetalle menuDetalle : listaDetalle) {
				listaMenuDetalle.add(Utilmfjpa.parseMenuDetalle(menuDetalle));
			}
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDetalle);
			if (UtilMfDto.listaNoVacia(listaMenuDetalle)) {
				status = HttpStatus.FOUND;
				mapeo.put(Constantes.VALOR_DATA_MAP, listaMenuDetalle);
			}
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		
		salida = new ResponseEntity<>(mapeo,status);

		return salida;
	}
	
	@PostMapping(value = "/menuDetalle")
	public ResponseEntity<Map<String, Object>> grabarDetalleMenuDia(@RequestBody MenuDetalleDto menudetalleDto){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(menudetalleDto, "menudetalleDto");
		
		try {
			menuDetalleRepository.save(Utilmfjpa.parseaMenuDetalleDto(menudetalleDto));
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<>(mapeo,status);
		
		return salida;
	}
}
