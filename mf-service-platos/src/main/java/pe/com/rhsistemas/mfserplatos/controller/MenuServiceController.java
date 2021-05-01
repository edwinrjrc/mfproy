/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.GenerarMenuDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfserplatos.service.MenuLogicaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/menuService")
public class MenuServiceController {

	private static final Logger log = LoggerFactory.getLogger(MenuServiceController.class);

	@Autowired
	private MenuLogicaService menuLogicaService;

	@PostMapping(value = "/menuGenerado", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Map<String, Object>> generarMenu(@RequestBody(required = true) GenerarMenuDto param) {
		ResponseEntity<Map<String, Object>> salida = null;

		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(param, "generarMenuDto");

			Integer idPersona = Integer.valueOf(param.getIdPersona());
			Integer idUsuario = Integer.valueOf(param.getIdUsuario());

			menuLogicaService.generarMenu(idPersona, idUsuario);

			salida = new ResponseEntity<>(HttpStatus.CREATED);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return salida;
	}

	@GetMapping(value = "/menuGenerado/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultarMenu(@PathVariable Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		try {
			HttpStatus estadoHttp = HttpStatus.NOT_FOUND;
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			
			MenuGeneradoDto menuGenerado = menuLogicaService.consultarMenuActivo(idPersona);
			Map<String, Object> mapeo = null;
			
			if (menuGenerado != null) {
				estadoHttp = HttpStatus.FOUND;
				mapeo = new HashMap<String, Object>();
				mapeo.put(Constantes.VALOR_DATA_MAP, menuGenerado);
			}
			
			salida = new ResponseEntity<>(mapeo, estadoHttp);
		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			salida = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return salida;
	}
}
