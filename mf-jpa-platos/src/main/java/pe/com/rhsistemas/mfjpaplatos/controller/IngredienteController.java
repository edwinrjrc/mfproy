package pe.com.rhsistemas.mfjpaplatos.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mfjpaplatos.dao.IngredienteRepository;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

@RestController
@RequestMapping( value = "/mf-jpa-platos")
public class IngredienteController {

	private static final Logger logger = LoggerFactory.getLogger(IngredienteController.class);
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
    @PostMapping(value = "/registrarIngrediente")
	public ResponseEntity<Map<String, Object>> registraIngrediente(@RequestBody IngredienteDto dto) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			ingredienteRepository.save(Utilmfjpa.parseIngredienteDto(dto));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
    
    @PostMapping(value = "/eliminarIngrediente")
	public ResponseEntity<Map<String, Object>> eliminarIngrediente(@RequestBody IngredienteDto dto) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			ingredienteRepository.delete(Utilmfjpa.parseIngredienteDto(dto));
			
			salida = new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
	
    @PostMapping(value = "/registrarIngredientes")
	public ResponseEntity<Map<String, Object>> registraIngredientes(@RequestBody List<IngredienteDto> lista) {
    	ResponseEntity<Map<String, Object>> salida = null;
		try {
			ingredienteRepository.saveAll(Utilmfjpa.parseListaIngredienteDto(lista));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;
	}
}
