/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.CambiarMenuDto;
import pe.com.rhsistemas.mf.post.dto.GenerarMenuDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfsermenu.service.MenuLogicaService;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/menuservice")
public class MenuServiceController {

	private static final Logger log = LoggerFactory.getLogger(MenuServiceController.class);

	@Autowired
	private MenuLogicaService menuLogicaService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/menugenerado")
	public ResponseEntity<Map<String, Object>> generarMenu(@RequestBody GenerarMenuDto param) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(param, "generarMenuDto");

			Integer idPersona = Integer.valueOf(param.getIdPersona());
			Integer idUsuario = Integer.valueOf(param.getIdUsuario());

			menuLogicaService.generarMenu(idPersona, idUsuario);

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
		} catch (UtilMfDtoException e) {
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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/menuGenerado/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultarMenu(@PathVariable Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros consultarMenu");
			UtilMfDto.pintaLog(idPersona, "idPersona");

			List<Map<String, Object>> menuSemanas = menuLogicaService.consultarMenuActivo(idPersona);

			if (UtilMfDto.listaNoVacia(menuSemanas)) {
				status = HttpStatus.OK;
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, menuSemanas);

		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/menuDetalle")
	public ResponseEntity<Map<String, Object>> cambiarPlatoMenu(@RequestBody CambiarMenuDto datos) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros cambiarPlatoMenu");
			UtilMfDto.pintaLog(datos, "datos");
			
			Date fechaInput = UtilMfDto.parseStringADate(datos.getFechaConsumo(), Constantes.FORMAT_DATE_MAPPER, null);
			
			log.info("fechaInput ::"+fechaInput);
			log.info("fechaInput 2::"+fechaInput.toString());
			
			String fechaStr = UtilMfDto.parseDateAString(fechaInput, "dd/MM/yyyy");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(UtilMfDto.parseStringADate(fechaStr, "dd/MM/yyyy",null));
			
			menuLogicaService.cambiarPlatoDia(UtilMfDto.parseStringAInteger(datos.getIdPersona()), UtilMfDto.parseStringAInteger(datos.getIdTipoPlato()), cal.get(Calendar.DAY_OF_WEEK));

			List<Map<String, Object>> menuSemanas = menuLogicaService.consultarMenuActivo(1);

			if (UtilMfDto.listaNoVacia(menuSemanas)) {
				status = HttpStatus.OK;
			}
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Exitoso");
			mapeo.put(Constantes.VALOR_DATA_MAP, menuSemanas);

		} catch (MfServiceMenuException e) {
			log.error(e.getMessage(), e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;

			mapeo = new HashMap<String, Object>();
			mapeo.put("error", true);
			mapeo.put("mensaje", "Operacion no completada");
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
