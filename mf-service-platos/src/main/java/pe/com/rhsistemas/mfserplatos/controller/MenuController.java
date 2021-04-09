/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mfserplatos.service.MenuLogicaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping( value = "/MenuRService")
public class MenuController {
	
	private static final Logger log = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuLogicaService menuLogicaService;
	
	@GetMapping(value = "/generarMenu")
	public ResponseEntity<Map<String, Object>> generarMenu(@RequestParam(name = "idPersona", required = true) Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		
		return salida;
	}
	
	@GetMapping(value = "/consultarMenu")
	public ResponseEntity<Map<String, Object>> consultarMenu() {
		return null;
	}
}
