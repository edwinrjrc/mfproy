/**
 * 
 */
package pe.com.rhsistemas.mfservicemail.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.DatosCorreo;
import pe.com.rhsistemas.mfservicemail.service.CorreoService;


/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/correoService")
public class CorreoController {

	private static final Logger log = LoggerFactory.getLogger(CorreoController.class);
	
	@Autowired
	private CorreoService correoService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/correoSinAdjunto")
	public ResponseEntity<Map<String, Object>> enviaCorreoSinAdjunto(@RequestBody DatosCorreo datosCorreo) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(datosCorreo, "datosCorreo");
			
			correoService.enviarCorreoSinAdjunto(datosCorreo);
			
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Envio de correo Correcto");

		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.BAD_REQUEST;

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
