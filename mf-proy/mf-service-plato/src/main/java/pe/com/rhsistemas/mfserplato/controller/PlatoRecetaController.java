/**
 * 
 */
package pe.com.rhsistemas.mfserplato.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;
import pe.com.rhsistemas.mfserplato.service.PlatoService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/platoRecetaservice")
public class PlatoRecetaController {

	private static final Logger log = LoggerFactory.getLogger(PlatoRecetaController.class);

	@Autowired
	private PlatoService platoService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/plato/{idPlato}")
	public ResponseEntity<Map<String, Object>> consultaCompletaPlato(@PathVariable Integer idPlato) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		log.info("Recibiendo parametros en consultaCompletaPlato");
		try {
			status = HttpStatus.NO_CONTENT;
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, platoService.consultarCompletaPlato(idPlato));
			status = HttpStatus.OK;
		} catch (MFServicePlatoException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");
		}

		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);
		return salida;
	}
}
