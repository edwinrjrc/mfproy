/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.GenerarMenuDto;
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
	
	@PostMapping(value = "/generarMenu", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> generarMenu(@RequestBody(required = true) GenerarMenuDto generarMenuDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		
		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(generarMenuDto, "generarMenuDto");
		
		Integer idPersona = Integer.valueOf(generarMenuDto.getIdPersona());
		Integer idUsuario = Integer.valueOf(generarMenuDto.getIdUsuario());
		
		menuLogicaService.generarMenu(idPersona, idUsuario);
		
		return salida;
	}
	
	@GetMapping(value = "/consultarMenu")
	public ResponseEntity<Map<String, Object>> consultarMenu() {
		return null;
	}
}
