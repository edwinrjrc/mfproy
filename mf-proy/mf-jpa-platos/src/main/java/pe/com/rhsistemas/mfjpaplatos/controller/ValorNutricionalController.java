/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.controller;

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

import pe.com.rhsistemas.mf.cross.dto.ValorNutricionalDto;
import pe.com.rhsistemas.mfjpaplatos.dao.ValorNutricionalRepository;
import pe.com.rhsistemas.mfjpaplatos.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping( value = "/mf-jpa-platos")
public class ValorNutricionalController {

	private static final Logger logger = LoggerFactory.getLogger(ValorNutricionalController.class);
	
	@Autowired
	private ValorNutricionalRepository repository;
	
	@PostMapping(value = "/registrarValorNutriIngrediente")
	public ResponseEntity<Map<String, Object>> registrarValorNutriIngrediente(@RequestBody ValorNutricionalDto valorNutricionalDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		
		try {
			repository.save(Utilmfjpa.parseValorNutricionalDto(valorNutricionalDto));
			
			salida = new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return salida;
	}
}
