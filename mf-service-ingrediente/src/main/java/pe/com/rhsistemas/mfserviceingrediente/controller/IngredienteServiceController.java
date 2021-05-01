/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
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
		try {
			HttpStatus estadoHttp = HttpStatus.NOT_FOUND;
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			ingredienteLogicaService.ingredientesPlato(idPlato);
			
			Map<String, Object> mapeo = new HashMap<>();
			
			salida = new ResponseEntity<>(mapeo, estadoHttp);
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return salida;
	}
}
