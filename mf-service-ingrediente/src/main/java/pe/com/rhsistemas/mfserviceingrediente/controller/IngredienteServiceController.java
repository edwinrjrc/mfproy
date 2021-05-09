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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
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
		HttpStatus estadoHttp = null;
		try {
			estadoHttp = HttpStatus.NOT_FOUND;
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPlato, "idPlato");
			
			List<PlatoIngredienteDto> listaIngredientes = ingredienteLogicaService.ingredientesPlato(idPlato);
			Map<String, Object> mapeo = null;
			if (UtilMfDto.listaNoVacia(listaIngredientes)) {
				mapeo = new HashMap<>();
				mapeo.put(Constantes.VALOR_DATA_MAP, listaIngredientes);
				estadoHttp = HttpStatus.FOUND;
			}
			
			salida = new ResponseEntity<>(mapeo, estadoHttp);
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		salida = new ResponseEntity<>(estadoHttp);

		return salida;
	}
	
	@PostMapping(value = "/ingredientes")
	public ResponseEntity<Map<String, Object>> ingredientesPlato(@RequestBody IngredientesPlatoDto ingredientesPlatoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(ingredientesPlatoDto, "ingredientesPlatoDto");
			
			ingredienteLogicaService.registrarIngredientesPlato(ingredientesPlatoDto.getIngredientes());
			
			estadoHttp = HttpStatus.CREATED;
		} catch (MfServiceIngredienteException e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		salida = new ResponseEntity<>(estadoHttp);
		
		return salida;
	}
	
}
