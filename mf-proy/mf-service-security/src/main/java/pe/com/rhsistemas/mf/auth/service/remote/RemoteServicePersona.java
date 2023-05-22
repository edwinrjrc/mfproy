/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.auth.exception.HttpClienteStatusConflict;
import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServicePersona {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteServicePersona.class);

	private static final String PATH_SERVICE = "http://mf-jpa-personas";

	private static final String PATH_CONTROLLER_1 = "/PersonasRJPAService";
	
	private static final String PATH_CONTROLLER_2 = "/UsuariosRJPAService";
	
	private static final String URL_SERVICE_1 = PATH_SERVICE + PATH_CONTROLLER_1 + "/personaNatural";

	private static final String URL_SERVICE_2 = PATH_SERVICE + PATH_CONTROLLER_2 + "/usuario";
	
	private static final String URL_SERVICE_3 = PATH_SERVICE + PATH_CONTROLLER_2 + "/correoUsuario";
	
	private static final String URL_SERVICE_4 = PATH_SERVICE + PATH_CONTROLLER_2 + "/credencialNueva";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public PersonaNaturalDto registrarPersonaNatural(PersonaNaturalDto personaNaturalDto) throws MfServiceSecurityException{
		PersonaNaturalDto personaDto = null;
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<PersonaNaturalDto> requestEntity = new HttpEntity<>(
					personaNaturalDto, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
			LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			personaDto = mapper.convertValue(dato, PersonaNaturalDto.class);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e,null);
		}
		return personaDto;
	}
	
	@SuppressWarnings("rawtypes")
	public UsuarioDto registrarUsuario(UsuarioDto usuarioDto) throws MfServiceSecurityException{
		try {
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<UsuarioDto> requestEntity = new HttpEntity<>(usuarioDto, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_2;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);

			log.info(respuesta.toString());
		} catch (HttpClientErrorException.Conflict e) {
			log.error(e.getMessage());
			throw new HttpClienteStatusConflict(e,e.getResponseBodyAsString());
		} catch (RestClientException e) {
			throw new MfServiceSecurityException(e,null);
		}
		return new UsuarioDto();
	}
	
	@SuppressWarnings("rawtypes")
	public List<UsuarioDto> consultarCorreoUsuario(UsuarioDto usuarioDto) throws MfServiceSecurityException{
		List<UsuarioDto> listaUsuario = null;
		try {
			//UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<UsuarioDto> requestEntity = new HttpEntity<>(obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_3 + "/" +usuarioDto.getLogin();
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
			List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			listaUsuario = new ArrayList<>();
			for (Object objeto : datosLista) {
				listaUsuario.add(mapper.convertValue((LinkedHashMap<?, ?>) objeto, UsuarioDto.class));
			}

		} catch (HttpClientErrorException.Conflict e) {
			log.error(e.getMessage());
			throw new HttpClienteStatusConflict(e,e.getResponseBodyAsString());
		} catch (RestClientException e) {
			throw new MfServiceSecurityException(e,null);
		}
		return listaUsuario;
	}
	
	public PersonaNaturalDto consultarPersonaNatural(Long idPersona) throws MfServiceSecurityException{
		PersonaNaturalDto personaDto = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1+"/"+idPersona;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
			LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			personaDto = mapper.convertValue(dato, PersonaNaturalDto.class);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e,null);
		}
		return personaDto;
	}
	
	public UsuarioDto actualizaCredencialUsuario(UsuarioDto usuarioDto) throws MfServiceSecurityException{
		try {
			HttpMethod metodoServicio = HttpMethod.PUT;

			log.info("Recibe parametros actualizaCredencial");
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			HttpEntity<UsuarioDto> requestEntity = new HttpEntity<UsuarioDto>(usuarioDto,obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_4;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
			LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			usuarioDto = mapper.convertValue(dato, UsuarioDto.class);
			
		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e,null);
		}
		return usuarioDto;
	}
	
	private URI obtenerUri(String cadenaUrl) throws MfServiceSecurityException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e,null);
		}
		return url;
	}

	private HttpHeaders obtenerHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
	
	private ObjectMapper obtenerMapper () {
		SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER_FULL);
		df.setTimeZone(TimeZone.getDefault());
		
		JsonFactory factory = new JsonFactory();
	    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
	    ObjectMapper mapper = new ObjectMapper(factory);
	    mapper.setDateFormat(df);
		mapper.setTimeZone(TimeZone.getDefault());
	    
	    return mapper;
	}
}
