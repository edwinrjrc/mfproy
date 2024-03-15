/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
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
import pe.com.rhsistemas.mf.auth.service.remote.RemoteServiceMail;
import pe.com.rhsistemas.mf.auth.service.remote.RemoteServicePersona;
import pe.com.rhsistemas.mf.auth.service.remote.RemoteServiceSolicitudRecuperaCredencial;
import pe.com.rhsistemas.mf.auth.web.controller.UserController;
import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.dto.RolDto;
import pe.com.rhsistemas.mf.cross.dto.SolicitudRecuperaCredencialDto;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.dto.ValidaCodigoSeguridadDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mf.post.dto.DatosCorreo;

/**
 * @author Edwin
 *
 */
@Service
public class PersonaServiceImpl implements PersonaService {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RemoteServicePersona remoteServicePersona;
	
	@Autowired
	private RemoteServiceSolicitudRecuperaCredencial remoteServiceSolicitudRecuperaCredencial;
	
	@Autowired
	private RemoteServiceMail remoteServiceMail;
	
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
	
    public PasswordEncoder passwordEncoder() {
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
		
		enviarCorreoRegistro(personaNaturalDto);
	}

	private void enviarCorreoRegistro(PersonaNaturalDto personaDto) {
		DatosCorreo datosCorreo = new DatosCorreo();
		
		try {
			String rutaTemplate = System.getenv().get("PATH_MAIL_TEMPLATE");
			
			File archivoTemplateCorreo = new File(rutaTemplate + File.separator + "correoregistrotemplate.txt");
			
			Scanner obj = new Scanner(archivoTemplateCorreo);

			StringBuffer sb = new StringBuffer();
			String textoLineaTemplate = "";
			while (obj.hasNextLine()) {
				textoLineaTemplate = obj.nextLine();
				StringUtils.replace(textoLineaTemplate,"[NOMBRE_USUARIO]",personaDto.getNombres());
				sb.append(textoLineaTemplate);
			}
			
			datosCorreo.setAsunto("¡Bienvenido a Menu Familiar!");
			datosCorreo.setMensaje(sb.toString());
			datosCorreo.setPara(personaDto.getCorreoElectronico());
			
			remoteServiceMail.enviarCorreo(datosCorreo);
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
	
	public boolean recuperarContrasena(String correoUsuario) throws MfServiceSecurityException {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setLogin(correoUsuario);
		usuarioDto.setEmail(correoUsuario);
		
		PersonaNaturalDto personaNaturalDto1 = new PersonaNaturalDto();
		personaNaturalDto1.setNombres("Allegra Rebaza");
		personaNaturalDto1.setCorreoElectronico("edwinrjrc@gmail.com");
		
		List<UsuarioDto> salida = remoteServicePersona.consultarCorreoUsuario(usuarioDto);
		
		if (UtilMfDto.listaNoVacia(salida)) {
			Timestamp hoyTimestamp = UtilMfDto.hoyTimestamp();
			
			SolicitudRecuperaCredencialDto solicitudDto = new SolicitudRecuperaCredencialDto();
			solicitudDto.setFechaRegistro(hoyTimestamp);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(hoyTimestamp);
			cal.add(Calendar.MINUTE, 15);
			
			Long idPersona = salida.get(0).getIdPersona();
			
			solicitudDto.setFechaCaducaSolicitud(UtilMfDto.parseDateASqlTimestamp(cal.getTime()));
			solicitudDto.setIdPersona(salida.get(0).getIdPersona());
			solicitudDto.setFechaModificacion(hoyTimestamp);
			solicitudDto.setIdUsuarioModificacion(idPersona.intValue());
			solicitudDto.setIdUsuarioRegistro(idPersona.intValue());
			solicitudDto.setFechaSolicitud(hoyTimestamp);
			
			SolicitudRecuperaCredencialDto salidaSolicitud = remoteServiceSolicitudRecuperaCredencial.registrarSolicitudRecuperaCredencial(solicitudDto);
			
			double idSolicitud = salidaSolicitud.getIdSolicitud();
			
			String codigoValidacion = calculoCodigoValidacion(idSolicitud, solicitudDto.getFechaSolicitud());
			
			PersonaNaturalDto personaNaturalDto = remoteServicePersona.consultarPersonaNatural(idPersona);
			
			enviarCorreoCodigoSeguridad(personaNaturalDto, codigoValidacion);
		}
		return false;
	}
	
	
	@Override
	public boolean validaCodigoSeguridad(ValidaCodigoSeguridadDto validacionCodigoSeguridad) throws MfServiceSecurityException {
		boolean validacionCodigo = false;
		try {
			UsuarioDto usuarioDto = new UsuarioDto();
			usuarioDto.setEmail(validacionCodigoSeguridad.getCorreoUsuario());
			usuarioDto.setLogin(validacionCodigoSeguridad.getCorreoUsuario());
			
			List<UsuarioDto> salida = remoteServicePersona.consultarCorreoUsuario(usuarioDto);
			
			if (UtilMfDto.listaNoVacia(salida)) {
				
				Long idPersona = salida.get(0).getIdPersona();
				
				SolicitudRecuperaCredencialDto solicitud = this.remoteServiceSolicitudRecuperaCredencial.buscarUltimaSolicitudRecuperaCredencial(idPersona);
				
				String codigoSeguridad = calculoCodigoValidacion(solicitud.getIdSolicitud().doubleValue(), solicitud.getFechaSolicitud());
				
				if (codigoSeguridad.equals(validacionCodigoSeguridad.getCodigoSeguridad())) {
					log.debug("codigo de seguridad de validacion correcto");
					validacionCodigo = false;
					
					if (UtilMfDto.hoyTimestamp().before(solicitud.getFechaCaducaSolicitud())) {
						validacionCodigo = true;
						log.debug("codigo esta a tiempo");
					}
					else if (UtilMfDto.hoyTimestamp().equals(solicitud.getFechaCaducaSolicitud())){
						validacionCodigo = true;
						log.debug("codigo es igual");
					}
					else {
						validacionCodigo = false;
						log.debug("codigo no esta a tiempo");
					}
				}
				else {
					log.debug("codigo de seguridad de validacion incorrecto");
				}
				
				log.debug(UtilMfDto.escribeObjetoEnLog(solicitud));
			}
			return validacionCodigo;
		} catch (MfServiceSecurityException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceSecurityException(e);
		} catch (UtilMfDtoException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceSecurityException(e);
		}
	}
	
	@Override
	public boolean actualizaCredencial(UsuarioDto usuarioDto) throws MfServiceSecurityException {
		boolean resultado = false;
		
		String credencialNueva = "";
		credencialNueva = usuarioDto.getPassword();
		credencialNueva = this.passwordEncoder().encode(credencialNueva);
		
		List<UsuarioDto> salida = remoteServicePersona.consultarCorreoUsuario(usuarioDto);
		usuarioDto = salida.get(0);
		usuarioDto.setPassword(credencialNueva);
		usuarioDto.setFechaModificacion(UtilMfDto.hoyTimestamp());
		
		remoteServicePersona.actualizaCredencialUsuario(usuarioDto);
		
		return resultado;
	}
	
	private String calculoCodigoValidacion(double idSolicitud, Timestamp fechaSolicitud) {
		String codigoValidacion = "";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaSolicitud);
		
		BigDecimal calculoCodigoValidacion = BigDecimal.valueOf(idSolicitud);
		
		calculoCodigoValidacion = calculoCodigoValidacion.multiply(BigDecimal.TEN);
		
		//calculoCodigoValidacion = calculoCodigoValidacion.add(calculoCodigoValidacion);
		
		calculoCodigoValidacion = calculoCodigoValidacion.add( (BigDecimal.TEN.add(BigDecimal.valueOf(-1))).multiply(BigDecimal.valueOf(cal.get(Calendar.DATE))) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.add( (BigDecimal.TEN.add(BigDecimal.valueOf(-2))).multiply(BigDecimal.valueOf(cal.get(Calendar.MONTH))) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.add( (BigDecimal.TEN.add(BigDecimal.valueOf(-3))).multiply(BigDecimal.valueOf(cal.get(Calendar.YEAR))) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.add( (BigDecimal.TEN.add(BigDecimal.valueOf(-4))).multiply(BigDecimal.valueOf(cal.get(Calendar.HOUR))) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.add( (BigDecimal.TEN.add(BigDecimal.valueOf(-4))).multiply(BigDecimal.valueOf(cal.get(Calendar.MINUTE))) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.multiply( calculoCodigoValidacion.divide( BigDecimal.valueOf(idSolicitud),2,RoundingMode.HALF_UP ) );
		
		calculoCodigoValidacion = calculoCodigoValidacion.setScale(0, RoundingMode.HALF_DOWN);
		
		codigoValidacion = calculoCodigoValidacion.toString();
		
		log.debug("Codigo Validacion ::"+codigoValidacion);
		
		return codigoValidacion;
	}
	
	private void enviarCorreoCodigoSeguridad(PersonaNaturalDto personaDto, String codigoSeguridad) {
		DatosCorreo datosCorreo = new DatosCorreo();
		
		try {
			String rutaTemplate = System.getenv().get("PATH_MAIL_TEMPLATE");
			
			File archivoTemplateCorreo = new File(rutaTemplate + File.separator + "correorecuperoclavetemplate.txt");
			
			Scanner obj = new Scanner(archivoTemplateCorreo);

			StringBuffer sb = new StringBuffer();
			String textoLineaTemplate = "";
			while (obj.hasNextLine()) {
				textoLineaTemplate = obj.nextLine();
				textoLineaTemplate = StringUtils.replace(textoLineaTemplate,"[NOMBRE_USUARIO]",personaDto.getNombres());
				textoLineaTemplate = StringUtils.replace(textoLineaTemplate,"[CODIGO_SEGURIDAD]",codigoSeguridad);
				sb.append(textoLineaTemplate);
			}
			
			datosCorreo.setAsunto("Codigo de Seguridad Cuenta Menu Familiar");
			datosCorreo.setMensaje(sb.toString());
			datosCorreo.setPara(personaDto.getCorreoElectronico());
			
			remoteServiceMail.enviarCorreo(datosCorreo);
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}
