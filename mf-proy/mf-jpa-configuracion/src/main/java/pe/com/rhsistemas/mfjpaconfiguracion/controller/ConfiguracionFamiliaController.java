/**
 * 
 */
package pe.com.rhsistemas.mfjpaconfiguracion.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpaconfiguracion.dao.ConfiguracionCuentaDao;
import pe.com.rhsistemas.mfjpaconfiguracion.entity.ConfiguracionFamilia;
import pe.com.rhsistemas.mfjpaconfiguracion.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "/ConfiguracionFamiliaRJPAService")
public class ConfiguracionFamiliaController {
	
	private static final Logger log = LoggerFactory.getLogger(ConfiguracionFamiliaController.class);
	
	@Autowired
	private ConfiguracionCuentaDao configuracionCuentaDao;

	@PostMapping(value = "/configuracionFamilia")
	public ResponseEntity<Map<String, Object>> guardarConfiguracionFamilia(@RequestBody ConfiguracionCuentaDto configuracionDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(configuracionDto, "configuracionDto");

			ConfiguracionFamilia entity = Utilmfjpa.parseConfiguracionCuentaDto(configuracionDto);
			log.debug(UtilMfDto.escribeObjetoEnLog(entity));
			configuracionCuentaDao.save(entity);

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");

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
	
	@GetMapping(value = "/configuracionFamilia/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultarConfiguracionFamilia(@PathVariable Integer idPersona) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		try {
			log.debug("Parametros recibidos consultarConfiguracionFamilia");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			
			Optional<ConfiguracionFamilia> resultadoConsulta = configuracionCuentaDao.findById(Long.valueOf(idPersona.longValue()));
			
			UtilMfDto.pintaLog(UtilMfDto.escribeObjetoEnLog(resultadoConsulta),"objeto");
			ConfiguracionCuentaDto configuracionDto = null;
			
			if (resultadoConsulta != null && !resultadoConsulta.isEmpty()) {
				configuracionDto = Utilmfjpa.parseConfiguracionFamilia(resultadoConsulta.get());
			}
			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, configuracionDto);
			

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
