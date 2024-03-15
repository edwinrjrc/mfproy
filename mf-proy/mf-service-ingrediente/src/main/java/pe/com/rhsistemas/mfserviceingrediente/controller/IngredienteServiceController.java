/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.IngredientesPlatoDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;
import pe.com.rhsistemas.mfserviceingrediente.service.IngredienteLogicaService;

/**
 * @author Edwin
 *
 */
@Controller
@RequestMapping(value = "/ingredienteService")
public class IngredienteServiceController {

	private static final Logger log = LoggerFactory.getLogger(IngredienteServiceController.class);
	
	@Autowired
	private IngredienteLogicaService ingredienteLogicaService;

	@GetMapping(value = "/ingredientes/{idPlato}")
	public ResponseEntity<Map<String, Object>> ingredientesPlato(@PathVariable Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros ingredientesPlato "+this.getClass().getName());
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			List<PlatoIngredienteDto> listaIngredientes = ingredienteLogicaService.ingredientesPlato(idPlato);
			if (UtilMfDto.listaNoVacia(listaIngredientes)) {
				status = HttpStatus.OK;
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredientes);
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
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
	
	@PostMapping(value = "/ingredientes")
	public ResponseEntity<Map<String, Object>> ingredientesPlato(@RequestBody IngredientesPlatoDto ingredientesPlatoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros ingredientesPlato "+this.getClass().getName());
			UtilMfDto.pintaLog(ingredientesPlatoDto, "ingredientesPlatoDto");
			
			ingredienteLogicaService.registrarIngredientesPlato(ingredientesPlatoDto.getIngredientes());
			
			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/ingredientes")
	public ResponseEntity<Map<String, Object>> listarIngredientes() {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros listarIngredientes "+this.getClass().getName());
			
			List<IngredienteDto> listaIngredientes = ingredienteLogicaService.listarIngredientes();
			if (UtilMfDto.listaNoVacia(listaIngredientes)) {
				status = HttpStatus.OK;
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredientes);
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
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
