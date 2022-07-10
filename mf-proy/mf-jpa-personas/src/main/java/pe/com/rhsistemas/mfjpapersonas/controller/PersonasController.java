/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaJuridicaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "PersonasRJPAService")
public class PersonasController {

	private static final Logger log = LoggerFactory.getLogger(PersonasController.class);
	
	@PostMapping(value = "/personaNatural")
	public ResponseEntity<Map<String, Object>> registrarMenu(@RequestBody PersonaNaturalDto personaNaturalDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(personaNaturalDto, "personaNaturalDto");

		try {

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
	
	@PostMapping(value = "/personaJuridica")
	public ResponseEntity<Map<String, Object>> registrarMenu(@RequestBody PersonaJuridicaDto personaJuridicaDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(personaJuridicaDto, "personaJuridicaDto");

		try {

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
}
