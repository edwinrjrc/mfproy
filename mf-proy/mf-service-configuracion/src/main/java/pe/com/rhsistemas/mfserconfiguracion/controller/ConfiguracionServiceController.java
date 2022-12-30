/**
 * 
 */
package pe.com.rhsistemas.mfserconfiguracion.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.ConfiguracionCuentaPostDto;
import pe.com.rhsistemas.mfserconfiguracion.exception.MfServiceConfiguracionException;
import pe.com.rhsistemas.mfserconfiguracion.service.ConfiguracionCuentaService;

/**
 * @author Edwin
 *
 */
@Controller
@RequestMapping(value = "/configuracionService")
public class ConfiguracionServiceController {

	private static final Logger log = LoggerFactory.getLogger(ConfiguracionServiceController.class);
	
	@Autowired
	private ConfiguracionCuentaService configuracionCuentaService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/configuracionCuenta")
	public ResponseEntity<Map<String, Object>> guardaConfiguracionCuenta(@RequestBody ConfiguracionCuentaPostDto configuracionCuentaDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros guardaConfiguracionCuenta");
			UtilMfDto.pintaLog(configuracionCuentaDto, "configuracionCuentaDto");
			
			String diasCocina = configuracionCuentaDto.getConfiguracionCuentaDto().getDiasCocinaSemana();
			diasCocina = StringUtils.substring(diasCocina, 0, StringUtils.length(diasCocina)-1);
			configuracionCuentaDto.getConfiguracionCuentaDto().setDiasCocinaSemana(diasCocina);
			
			configuracionCuentaService.guardaConfiguracionCuenta(configuracionCuentaDto.getConfiguracionCuentaDto());
			
			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
		} catch (MfServiceConfiguracionException e) {
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
	@GetMapping(value = "/configuracionCuenta/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultarConfiguracionCuenta(@PathVariable(name = "idPersona") Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			status = HttpStatus.NO_CONTENT;
			log.info("Recibiendo parametros consultarConfiguracionCuenta");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			
			ConfiguracionCuentaDto resultadoConsulta = configuracionCuentaService.consultarConfiguracionFamilia(idPersona);
			
			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Existo");
			mapeo.put(Constantes.VALOR_DATA_MAP, resultadoConsulta);
			
		} catch (MfServiceConfiguracionException e) {
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
