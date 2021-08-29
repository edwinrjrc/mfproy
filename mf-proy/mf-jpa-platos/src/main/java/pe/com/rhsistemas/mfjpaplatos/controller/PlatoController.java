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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
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
	
	@PostMapping(value = "/plato")
	public ResponseEntity<Map<String, Object>> registrarPlato(@RequestBody PlatoDto platoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			platoRepository.save(Utilmfjpa.parsePlatoDto(platoDto));

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		
		return salida;
	}

	@PostMapping(value = "/ingredientesPlato")
	public ResponseEntity<Map<String, Object>> registraIngredientesPlato(@RequestBody PlatoDto platoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			platoRepository.save(Utilmfjpa.parsePlatoDto(platoDto));

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		return salida;
	}

	@GetMapping(value = "/platos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> consultarPlatos() {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			List<Plato> platos = platoRepository.findAll();
			List<PlatoDto> platosDto = new ArrayList<>();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		return salida;
	}

	@GetMapping(value = "/platosNoConsumidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> platosNoConsumidos(
			@RequestParam(name = "idPersona", required = true) Integer idPersona,
			@RequestParam(name = "fechaCorteDesde", required = true) Date fechaCorteDesde,
			@RequestParam(name = "fechaCorteHasta", required = true) Date fechaCorteHasta) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			logger.info("recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");

			List<Plato> platos = platoRepository.platosNoConsumidos(idPersona,
					UtilMfDto.convertirUtilDateASqlDate(fechaCorteDesde), UtilMfDto.convertirUtilDateASqlDate(fechaCorteHasta));
			List<PlatoDto> platosDto = new ArrayList<>();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<>(mapeo, status);
		return salida;
	}
	
	@GetMapping(value = "/platosNoConsumidosTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> platosNoConsumidos(
			@RequestParam(name = "idPersona", required = true) Integer idPersona,
			@RequestParam(name = "fechaCorteDesde", required = true) Date fechaCorteDesde,
			@RequestParam(name = "fechaCorteHasta", required = true) Date fechaCorteHasta,
			@RequestParam(name = "idTipoPlato", required = true) Integer idTipoPlato
			) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			logger.info("recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");

			List<Plato> platos = platoRepository.platosNoConsumidosTipo(idPersona,
					UtilMfDto.convertirUtilDateASqlDate(fechaCorteDesde), UtilMfDto.convertirUtilDateASqlDate(fechaCorteHasta),idTipoPlato);
			List<PlatoDto> platosDto = new ArrayList<>();
			for (Plato plato : platos) {
				platosDto.add(Utilmfjpa.parsePlatoEntity(plato));
			}
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, platosDto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
		}
		salida = new ResponseEntity<>(mapeo, status);
		return salida;
	}
	
}
