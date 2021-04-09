/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMf;
import pe.com.rhsistemas.mfjpaplatos.dao.PlatoRepository;
import pe.com.rhsistemas.mfjpaplatos.entity.Plato;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/PlatoRJPAService")
public class PlatoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatoController.class);
	
	@Autowired
	private PlatoRepository platoRepository;

	@PostMapping(value = "/registrarPlato")
	public ResponseEntity<Map<String, Object>> registrarPlato(@RequestBody PlatoDto platoDto) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			platoRepository.save(Utilmfjpa.parsePlatoDto(platoDto));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
	@PostMapping(value = "/registrarIngredientesPlato")
	public ResponseEntity<Map<String, Object>> registraIngredientesPlato(@RequestBody PlatoDto platoDto) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			platoRepository.save(Utilmfjpa.parsePlatoDto(platoDto));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
	@GetMapping(value = "/consultarPlatos")
	public ResponseEntity<Map<String, Object>> consultarPlatos() {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			Map<String, Object> mapeo = new HashMap<String, Object>();
			List<Plato> platos =  platoRepository.findAll();
			List<PlatoDto> platosDto = new ArrayList<>();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);
			
			salida = new ResponseEntity<>(mapeo, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error en consultarPlatos", e);
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
	@GetMapping(value = "/platosNoConsumidos")
	public ResponseEntity<Map<String, Object>> platosNoConsumidos(@RequestParam(name = "idPersona", required = true) Integer idPersona, @RequestParam(name = "fechaRango", required = true) Date fechaRango) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			Map<String, Object> mapeo = new HashMap<String, Object>();
			List<Plato> platos =  platoRepository.platosNoConsumidos(idPersona, UtilMf.convertirUtilDateASqlDate(fechaRango));
			List<PlatoDto> platosDto = new ArrayList<>();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);
			
			salida = new ResponseEntity<>(mapeo, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error en platosNoConsumidos", e);
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
	@GetMapping(value = "/consultarPlatos2")
	@ResponseBody
	public List<PlatoDto> consultarPlatos2() {
		List<PlatoDto> platosDto = new ArrayList<>();
		try {
			Map<String, Object> mapeo = new HashMap<String, Object>();
			List<Plato> platos =  platoRepository.findAll();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);
			
		} catch (Exception e) {
			logger.error("Error en consultarPlatos", e);
			logger.error(e.getMessage(), e);
		}
		return platosDto;
	}
}
