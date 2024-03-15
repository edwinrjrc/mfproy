/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PersonaJuridicaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpapersonas.dao.PersonaJuridicaRepository;
import pe.com.rhsistemas.mfjpapersonas.dao.PersonaNaturalRepository;
import pe.com.rhsistemas.mfjpapersonas.dao.PersonaRepository;
import pe.com.rhsistemas.mfjpapersonas.entity.Persona;
import pe.com.rhsistemas.mfjpapersonas.entity.PersonaNatural;
import pe.com.rhsistemas.mfjpapersonas.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "PersonasRJPAService")
public class PersonasController {

	private static final Logger log = LoggerFactory.getLogger(PersonasController.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private PersonaJuridicaRepository personaJuridicaRepository;
	@Autowired
	private PersonaNaturalRepository personaNaturalRepository;


	@PostMapping(value = "/personaNatural")
	public ResponseEntity<Map<String, Object>> registrarPersonaNatural(
			@RequestBody PersonaNaturalDto personaNaturalDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos registrarPersonaNatural "+this.getClass().getName());
			UtilMfDto.pintaLog(personaNaturalDto, "personaNaturalDto");
			
			Persona personaEntity = Utilmfjpa.parsePersonaDto(personaNaturalDto);
			personaEntity.setPersonaNatural(Utilmfjpa.parsePersonaNaturalDto(personaNaturalDto));
			
			Persona salidaPersona = personaRepository.saveAndFlush(personaEntity);

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, Utilmfjpa.parsePersonaNatural(salidaPersona));
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
	
	@GetMapping(value = "/personaNatural/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultarPersonaNatural(
			@PathVariable Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos consultarPersonaNatural "+this.getClass().getName());
			UtilMfDto.pintaLog(idPersona, "idPersona");
			
			Optional<PersonaNatural> salidaPersona = personaNaturalRepository.findById( UtilMfDto.parseIntegerALong(idPersona));
			
			PersonaNaturalDto personaDto = Utilmfjpa.parsePersonaNatural(salidaPersona.get());

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, personaDto);
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
	public ResponseEntity<Map<String, Object>> registrarPersonaJuridica(
			@RequestBody PersonaJuridicaDto personaJuridicaDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.debug("Parametros recibidos registrarPersonaJuridica "+this.getClass().getName());
			UtilMfDto.pintaLog(personaJuridicaDto, "personaJuridicaDto");

			personaJuridicaRepository.save(Utilmfjpa.parsePersonaJuridicaDto(personaJuridicaDto));

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
