/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.controller;

import java.util.HashMap;
import java.util.Map;

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
import pe.com.rhsistemas.mf.cross.dto.SolicitudRecuperaCredencialDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpapersonas.dao.SolicitudRecuperoCredencialRepository;
import pe.com.rhsistemas.mfjpapersonas.entity.SolicitudRecuperaCredencial;
import pe.com.rhsistemas.mfjpapersonas.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "SolicitudRecuperaCredencialRJPAService")
public class SolicitudRecuperaCredencialController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonasController.class);
	
	@Autowired
	private SolicitudRecuperoCredencialRepository solicitudRecuperoCredencialRepository;
	
	@PostMapping(value = "/solicitudRecuperaCredencial")
	public ResponseEntity<Map<String, Object>> registrarSolicitudRecupero(@RequestBody SolicitudRecuperaCredencialDto solicitudRecupera){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(solicitudRecupera, "solicitudRecupera");
			
			SolicitudRecuperaCredencial salidaRegistro = solicitudRecuperoCredencialRepository.save(Utilmfjpa.parseaSolicitudRecuperaCredencial(solicitudRecupera));

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, Utilmfjpa.parseaSolicitudRecuperaCredencialDto(salidaRegistro));
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
	
	@GetMapping(value = "/solicitudRecuperaCredencial/{idPersona}")
	public ResponseEntity<Map<String, Object>> consultaUltimaSolicitudRecupero(@PathVariable(name = "idPersona", required = true) Long idPersona){
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			
			SolicitudRecuperaCredencial solicitud = solicitudRecuperoCredencialRepository.buscarUltimaSolicitud(idPersona);

			status = HttpStatus.CREATED;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, Utilmfjpa.parseaSolicitudRecuperaCredencialDto(solicitud));
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
