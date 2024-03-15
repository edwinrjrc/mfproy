/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.CambiarPlatoMenuDiaDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfsermenu.service.MenuLogicaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/platoMenuservice")
public class PlatoMenuServiceController {

	private static final Logger log = LoggerFactory.getLogger(PlatoMenuServiceController.class);
	
	@Autowired
	private MenuLogicaService menuLogicaService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/platoMenuDia")
	public ResponseEntity<Map<String, Object>> cambiarPlatoMenuDia(@RequestBody CambiarPlatoMenuDiaDto param) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Recibiendo parametros cambiarPlatoMenuDia "+this.getClass().getName());
			UtilMfDto.pintaLog(param, "cambiarPlatoMenuDia");
			
			Date fechaConsumo = UtilMfDto.parseStringADate(param.getFechaConsumo(), Constantes.FORMAT_DATE_MAPPER, null);

			menuLogicaService.cambiarPlatoMenuDia(param.getIdMenu(), fechaConsumo, param.getIdPlato());

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Generacion Correcta");

		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.BAD_REQUEST;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion no completada");

		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

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
