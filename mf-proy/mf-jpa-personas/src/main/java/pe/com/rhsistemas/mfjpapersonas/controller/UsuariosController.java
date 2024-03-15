/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
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
			log.debug("Parametros recibidos registrarUsuario "+this.getClass().getName());
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			Usuario usuarioEntity = Utilmfjpa.paseUsuario(usuarioDto);
			
			List<Usuario> listadoResultado = usuarioRepository.findByTxLogin(usuarioDto.getLogin());
			
			if (!UtilMfDto.listaNoVacia(listadoResultado)) {
				usuarioRoleRepository.saveAll(usuarioEntity.getUsuariosRoles());
				usuarioRepository.save(Utilmfjpa.paseUsuario(usuarioDto));

				status = HttpStatus.CREATED;
				mapeo = new HashMap<String, Object>();
				mapeo.put("error", false);
				mapeo.put("mensaje", "Operacion Completada");
			}
			else {
				status = HttpStatus.CONFLICT;
				mapeo = new HashMap<String, Object>();
				mapeo.put("error", true);
				mapeo.put("mensaje", "Usuario ya registrado");
			}
			
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
	
	@GetMapping(value = "/correoUsuario/{loginUsuario}")
	public ResponseEntity<Map<String, Object>> consultarCorreoUsuario(@PathVariable(name = "loginUsuario", required = true) String loginUsuario) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("Parametros recibidos consultarCorreoUsuario "+this.getClass().getName());
			UtilMfDto.pintaLog(loginUsuario, "loginUsuario");
			
			List<Usuario> listaUsuarios = usuarioRepository.findByTxLogin(loginUsuario);
			
			List<UsuarioDto> listaUsuariosDto = null;
			
			listaUsuariosDto = new ArrayList<>();
			if (UtilMfDto.listaNoVacia(listaUsuarios)) {
				for (Usuario usuario : listaUsuarios) {
					listaUsuariosDto.add(Utilmfjpa.paseUsuario(usuario));
				}
			}
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, listaUsuariosDto);
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
	
	@PutMapping(value = "/credencialNueva")
	public ResponseEntity<Map<String, Object>> actualizaCredencial(@RequestBody UsuarioDto usuarioDto) {
		ResponseEntity<Map<String, Object>> salida = null;
		Map<String, Object> mapeo = null;
		HttpStatus status = null;
		
		try {
			log.debug("Parametros recibidos actualizaCredencial "+this.getClass().getName());
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			Usuario usuarioActualizado = usuarioRepository.save(Utilmfjpa.paseUsuario(usuarioDto));
			
			status = HttpStatus.OK;
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Operacion Completada");
			mapeo.put(Constantes.VALOR_DATA_MAP, Utilmfjpa.paseUsuario(usuarioActualizado));
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
