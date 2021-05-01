/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaingrediente.dao.IngredienteRepository;
import pe.com.rhsistemas.mfjpaingrediente.dao.PlatoIngredienteRepository;
import pe.com.rhsistemas.mfjpaingrediente.entity.Ingrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.util.Utilmfjpa;

@RestController
@RequestMapping( value = "/IngredienteRJPAService")
public class IngredienteController {

	private static final Logger log = LoggerFactory.getLogger(IngredienteController.class);
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
    @PostMapping(value = "/ingrediente")
	public ResponseEntity<Map<String, Object>> registraIngrediente(@RequestBody IngredienteDto dto) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(dto,"ingredienteDto");
			
			ingredienteRepository.save(Utilmfjpa.parseIngredienteDto(dto));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
    
    @DeleteMapping(value = "/ingrediente/{idIngrediente}")
	public ResponseEntity<Map<String, Object>> eliminarIngrediente(@PathVariable Integer idIngrediente) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(idIngrediente,"idIngrediente");
			
			Ingrediente entity = new Ingrediente();
			entity.setIdIngrediente(idIngrediente);
			ingredienteRepository.delete(entity);
			
			salida = new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
    @PostMapping(value = "/ingredientes")
	public ResponseEntity<Map<String, Object>> registraIngredientes(@RequestBody List<IngredienteDto> lista) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(lista,"listaIngredientes");
			ingredienteRepository.saveAll(Utilmfjpa.parseListaIngredienteDto(lista));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
    
    @GetMapping(value = "/ingredientes/{idPlato}")
    public ResponseEntity<Map<String, Object>> ingredientesPlato(@PathVariable Integer idPlato){
    	ResponseEntity<Map<String, Object>> salida = null;
    	HttpStatus statusHttp = null;
    	
    	log.debug("Parametros recibidos");
		UtilMfDto.pintaLog(idPlato,"idPlato");
		
    	List<PlatoIngrediente> listaPlatoIngredientes = platoIngredienteRepository.findAllByPlato(idPlato);
    	
    	statusHttp = HttpStatus.NOT_FOUND;
    	
    	Map<String, Object> mapeo = null;
    	
    	if (UtilMfDto.listaNoVacia(listaPlatoIngredientes)) {
    		mapeo = new HashMap<String, Object>();
    		mapeo.put(Constantes.VALOR_DATA_MAP, listaPlatoIngredientes);
    		statusHttp = HttpStatus.FOUND;
    	}
    	salida = new ResponseEntity<>(mapeo,statusHttp);
    	
    	return salida;
    }
}