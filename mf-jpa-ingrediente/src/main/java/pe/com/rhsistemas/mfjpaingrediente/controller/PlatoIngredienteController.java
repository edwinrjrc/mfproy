/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.IngredientesPlatoDto;
import pe.com.rhsistemas.mfjpaingrediente.dao.PlatoIngredienteRepository;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/PlatoIngredienteRJPAService")
public class PlatoIngredienteController {

	private static final Logger log = LoggerFactory.getLogger(PlatoIngredienteController.class);

	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
	@PostMapping(value = "/ingredientes", consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> registraIngredientesPlato(@RequestBody(required = true) List<PlatoIngredienteDto> listaIngredientePlatoDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		HttpStatus estadoHttp = null;
		Map<String, Object> mapeo = null;
		
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(listaIngredientePlatoDto, "listaIngredientePlatoDto");
			
			List<PlatoIngrediente> entities = new ArrayList<PlatoIngrediente>();
			
			for (PlatoIngredienteDto platoIngredienteDto : listaIngredientePlatoDto) {
				entities.add(Utilmfjpa.parsePlatoIngredienteDto(platoIngredienteDto));
			}
			
			platoIngredienteRepository.saveAll(entities);
			estadoHttp = HttpStatus.CREATED;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			estadoHttp = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		salida = new ResponseEntity<Map<String, Object>>(mapeo, estadoHttp);
		
		return salida;
	}
}
