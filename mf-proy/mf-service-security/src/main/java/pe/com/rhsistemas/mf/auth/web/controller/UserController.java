package pe.com.rhsistemas.mf.auth.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.service.UserService;
import pe.com.rhsistemas.mf.auth.util.Utilmfsecurity;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

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

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Usuario createUser(@RequestBody UsuarioDto user) {
        //log.info("process=create-user, user_email={}", user.getEmail());
        return userService.createUser(Utilmfsecurity.parseUsuario(user));
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
