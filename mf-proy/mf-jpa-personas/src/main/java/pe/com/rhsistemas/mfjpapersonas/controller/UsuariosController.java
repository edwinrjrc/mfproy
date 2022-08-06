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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpapersonas.dao.UsuarioRepository;
import pe.com.rhsistemas.mfjpapersonas.dao.UsuarioRoleRepository;
import pe.com.rhsistemas.mfjpapersonas.entity.Usuario;
import pe.com.rhsistemas.mfjpapersonas.util.Utilmfjpa;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping(value = "UsuariosRJPAService")
public class UsuariosController {

	private static final Logger log = LoggerFactory.getLogger(UsuariosController.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;
	
	@PostMapping(value = "/usuario")
	public ResponseEntity<Map<String, Object>> registrarUsuario(
			@RequestBody UsuarioDto usuarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;

		try {
			status = HttpStatus.NO_CONTENT;
			log.debug("Parametros recibidos");
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			Usuario usuarioEntity = Utilmfjpa.paseUsuario(usuarioDto);
			
			usuarioRoleRepository.saveAll(usuarioEntity.getUsuariosRoles());
			
			usuarioRepository.save(Utilmfjpa.paseUsuario(usuarioDto));

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
}
