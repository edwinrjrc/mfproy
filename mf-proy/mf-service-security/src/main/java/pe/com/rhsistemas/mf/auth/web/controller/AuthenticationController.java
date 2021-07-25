package pe.com.rhsistemas.mf.auth.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.exception.AutenticacionException;
import pe.com.rhsistemas.mf.auth.model.AuthenticationRequest;
import pe.com.rhsistemas.mf.auth.model.ChangePassword;
import pe.com.rhsistemas.mf.auth.model.UserTokenState;
import pe.com.rhsistemas.mf.auth.security.TokenHelper;
import pe.com.rhsistemas.mf.auth.service.CustomUserDetailsService;
import pe.com.rhsistemas.mf.auth.service.UserService;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	TokenHelper tokenHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/auth/login")
	public ResponseEntity<Map<String, Object>> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws AutenticacionException {
		ResponseEntity<Map<String, Object>> salida = null;
		log.info("Recibiendo parametros");
		UtilMfDto.pintaLog(authenticationRequest, "authenticationRequest");
		Map<String, Object> mapeo = null;
		UserTokenState userToken = null;
		HttpStatus status = null;
		try {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String jws = tokenHelper.generateToken(userDetails.getUsername());
			long expiresIn = tokenHelper.getExpiredIn();

			userToken = new UserTokenState(jws, expiresIn);
			Usuario usuario = userService.findByTxLogin(userDetails.getUsername());
			userToken.setIdUsuario(usuario.getIdPersona().intValue());

			mapeo = new HashMap<String, Object>();
			mapeo.put("userToken", userToken);
			mapeo.put("error", false);
			mapeo.put("mensaje", "Autenticacion Correcta");

			status = HttpStatus.OK;

		} catch (BadCredentialsException e) {
			log.error(e.getMessage(), e);

			mapeo = new HashMap<String, Object>();
			mapeo.put("mensaje", "Usuario y/o contraseña incorrectos");
			mapeo.put("error", true);

			status = HttpStatus.UNAUTHORIZED;
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			mapeo = new HashMap<String, Object>();
			mapeo.put("mensaje", "Operacion no completada");
			mapeo.put("error", true);

			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		salida = new ResponseEntity<Map<String, Object>>(mapeo, status);

		return salida;
	}

	@PostMapping(value = "/auth/refresh")
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, Principal principal) {
		String authToken = tokenHelper.getToken(request);
		if (authToken != null && principal != null) {
			String refreshedToken = tokenHelper.refreshToken(authToken);
			long expiresIn = tokenHelper.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

	@RequestMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public Usuario user(Principal user) {
		return this.userService.findByTxLogin(user.getName());
	}

	@PostMapping(value = "/change-password")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
		userDetailsService.changePassword(changePassword.getOldPassword(), changePassword.getNewPassword());
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}
}