package pe.com.rhsistemas.mf.auth.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.exception.HttpClienteStatusConflict;
import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.auth.service.PersonaService;
import pe.com.rhsistemas.mf.auth.service.UserService;
import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.UsuarioBeanDto;

@RestController
@RequestMapping("/api")
//@Slf4j
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    
    @Autowired()
    private PersonaService personaService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<Usuario> getUsers() {
        //log.info("process=get-users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable Long id) {
        //log.info("process=get-user, user_id={}", id);
        Optional<Usuario> user = userService.getUserById(id);
        return user.map( u -> ResponseEntity.ok(u))
                   .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users")
    //@ResponseStatus(CREATED)
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UsuarioBeanDto usuarioBean) {
    	log.info("creacion de usuario");
    	Map<String, Object> mapeo = null;
    	HttpStatus status = null;
    	ResponseEntity<Map<String, Object>> salida = null;
    	try {
    		status = HttpStatus.NO_CONTENT;
			log.info(UtilMfDto.escribeObjetoEnLog(usuarioBean));
			
			personaService.registrarUsuario(usuarioBean.getUsuario());
			
			mapeo = new HashMap<String, Object>();
			mapeo.put("error", false);
			mapeo.put("mensaje", "Registro Usuario");

			status = HttpStatus.CREATED;
		} catch (UtilMfDtoException e) {
			log.error(e.getMessage(),e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("mensaje", Constantes.MSJE_DEFECTO_SERVICIO_NO_COMPLETADO);
			mapeo.put("error", true);
		} catch (HttpClienteStatusConflict e) {
			log.error("HttpConflicto");
			log.error(e.getMessage(),e);
			try {
				Map<?, ?> mapeoError = UtilMfDto.parseStringMap(e.getCuerpoMensaje());
				status = HttpStatus.CONFLICT;
				mapeo = new HashMap<String, Object>();
				mapeo.put("mensaje", mapeoError.get("mensaje").toString());
				mapeo.put("error", true);
			} catch (UtilMfDtoException e1) {
				log.error(e.getMessage(),e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				mapeo = new HashMap<String, Object>();
				mapeo.put("mensaje", Constantes.MSJE_DEFECTO_SERVICIO_NO_COMPLETADO);
				mapeo.put("error", true);
			}
		} catch (MfServiceSecurityException e) {
			log.error(e.getMessage(),e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("mensaje", Constantes.MSJE_DEFECTO_SERVICIO_NO_COMPLETADO);
			mapeo.put("error", true);
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mapeo = new HashMap<String, Object>();
			mapeo.put("mensaje", Constantes.MSJE_DEFECTO_SERVICIO_NO_COMPLETADO);
			mapeo.put("error", true);
		}
    	salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
    }

    @PutMapping("/{id}")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario user) {
        //log.info("process=update-user, user_id={}", id);
        //user.setId(id);
        return userService.updateUser(user);
    }

    /*@DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        //log.info("process=delete-user, user_id={}", id);
        userService.deleteUser(id);
    }*/

}
