/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.auth.service.PersonaService;
import pe.com.rhsistemas.mf.auth.service.remote.RemoteServicePersona;
import pe.com.rhsistemas.mf.auth.web.controller.UserController;
import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.dto.RolDto;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

/**
 * @author Edwin
 *
 */
@Service
public class PersonaServiceImpl implements PersonaService {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RemoteServicePersona remoteServicePersona;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
	}

	@Override
	public PersonaDto registrarPersonaNatural(PersonaNaturalDto dto) throws MfServiceSecurityException {
		return remoteServicePersona.registrarPersonaNatural(dto);
	}

	@Override
	public PersonaDto registrarPersonaJuridica(PersonaNaturalDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void registrarUsuario(UsuarioDto usuario) throws MfServiceSecurityException {
		int idUsuarioAdmin = 1;
		String estadoUsuario = "A";
		String inCaducaPassword = "1";
		Date fechaHoy = UtilMfDto.hoyDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaHoy);
		cal.add(Calendar.MONTH, 3);
		
		PersonaNaturalDto personaNaturalDto = new PersonaNaturalDto();
		personaNaturalDto.setNombres(usuario.getNombres());
		personaNaturalDto.setPrimerApellido(usuario.getApellidos());
		personaNaturalDto.setCorreoElectronico(UtilMfDto.validaNulo(usuario.getLogin(), usuario.getEmail()).toString());
		personaNaturalDto.setIdUsuarioRegistro(idUsuarioAdmin);
		personaNaturalDto.setFechaRegistro(UtilMfDto.hoyTimestamp());
		personaNaturalDto.setIdUsuarioModificacion(idUsuarioAdmin);
		personaNaturalDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
		
		PersonaDto personaDto = remoteServicePersona.registrarPersonaNatural(personaNaturalDto);
		
		String passSinEncryp = usuario.getPassword(); 
		
		usuario.setPassword(passwordEncoder().encode(passSinEncryp));
		usuario.setFechaRegistro(UtilMfDto.hoyTimestamp());
		usuario.setFechaModificacion(UtilMfDto.hoyTimestamp());
		usuario.setIdUsuarioRegistro(idUsuarioAdmin);
		usuario.setIdUsuarioModificacion(idUsuarioAdmin);
		usuario.setFechaCaduca(UtilMfDto.parseDateASqlTimestamp(cal.getTime()));
		usuario.setFechaUltActualizaPass(UtilMfDto.hoyTimestamp());
		usuario.setIdPersona(personaDto.getId());
		usuario.setLogin(personaNaturalDto.getCorreoElectronico());
		usuario.setEstado(estadoUsuario);
		usuario.setInCuentaCaduca(inCaducaPassword);
		
		List<RolDto> roles = new ArrayList<RolDto>();
		RolDto rol = new RolDto();
		rol.setIdrol(2);
		rol.setFechaModificacion(UtilMfDto.hoyTimestamp());
		rol.setFechaRegistro(UtilMfDto.hoyTimestamp());
		rol.setIdUsuarioModificacion(1);
		rol.setIdUsuarioRegistro(1);
		roles.add(rol);
		
		usuario.setListaRoles(roles);
		
		remoteServicePersona.registrarUsuario(usuario);
		
		enviarCorreoRegistro(personaDto);
	}

	private void enviarCorreoRegistro(PersonaDto personaDto) {
		// TODO Auto-generated method stub
		
	}

}
