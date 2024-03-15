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
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;
import pe.com.rhsistemas.mfserviceingrediente.service.IngredienteLogicaService;

/**
 * @author Edwin
 *
 */
@Controller
@RequestMapping(value = "/unidadMedidaService")
public class UnidadMedidaServiceController {
	
	private static final Logger log = LoggerFactory.getLogger(UnidadMedidaServiceController.class);
	
	@Autowired
	private IngredienteLogicaService ingredienteLogicaService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/unidadesMedida")
	public ResponseEntity<Map<String, Object>> listarMedidas() {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("Recibiendo parametros UnidadMedidaServiceController "+this.getClass().getName());
			status = HttpStatus.NO_CONTENT;
			
			List<UnidadMedidaDto> listaUnidades = ingredienteLogicaService.listarUnidadesMedida();
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaUnidades);
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
