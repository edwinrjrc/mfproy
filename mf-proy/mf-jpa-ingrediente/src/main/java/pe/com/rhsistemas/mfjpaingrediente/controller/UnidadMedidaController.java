/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mfjpaingrediente.dao.UnidadMedidaRepository;
import pe.com.rhsistemas.mfjpaingrediente.entity.UnidadMedida;
import pe.com.rhsistemas.mfjpaingrediente.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/UnidadMedidaRJPAService")
public class UnidadMedidaController {

	private static final Logger log = LoggerFactory.getLogger(UnidadMedidaController.class);
	
	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@GetMapping(value = "/UnidadesMedida")
	public ResponseEntity<Map<String, Object>> listarUnidadesMedida() {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		List<UnidadMedidaDto> listaUnidadMedida = null;
		try {
			
			List<UnidadMedida> listaUnidades = unidadMedidaRepository.findAll();
			if (listaUnidades != null) {
				listaUnidadMedida = new ArrayList<>();
				for (UnidadMedida unidadMedida : listaUnidades) {
					listaUnidadMedida.add(Utilmfjpa.parseUnidadMedidad(unidadMedida));
				}
			}

			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put(Constantes.VALOR_DATA_MAP, listaUnidadMedida);
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
