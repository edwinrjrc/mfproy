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
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;
import pe.com.rhsistemas.mfserviceingrediente.service.IngredienteLogicaService;

/**
 * @author Edwin
 *
 */
@Controller
@RequestMapping(value = "/ingredienteMenuService")
public class IngredientesMenuServiceController {

	private static final Logger log = LoggerFactory.getLogger(IngredientesMenuServiceController.class);
	
	@Autowired
	private IngredienteLogicaService ingredienteLogicaService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/ingredientes/{idMenu}/{idUsuario}")
	public ResponseEntity<Map<String, Object>> ingredientesMenu(@PathVariable Integer idMenu, @PathVariable Integer idUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros ingredientesMenu");
			UtilMfDto.pintaLog(idMenu, "idMenu");
			UtilMfDto.pintaLog(idUsuario, "idUsuario");
			
			List<PlatoIngredienteDto> listaIngredientes = ingredienteLogicaService.listarIngredientesMenu(idMenu);
			
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/ingredientesExport/{idMenu}")
	public ResponseEntity<Map<String, Object>> ingredientesExportMenu(@PathVariable Long idMenu) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros ingredientesMenu");
			UtilMfDto.pintaLog(idMenu, "idMenu");
			
			Map<String, Object> salidaPdf = ingredienteLogicaService.listarPlatoIngredientesMenu(idMenu);
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, salidaPdf);
			status = HttpStatus.OK;
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
