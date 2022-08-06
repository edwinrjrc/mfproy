/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
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

	private static final String URL_SERVICE_2 = PATH_SERVICE + PATH_CONTROLLER_2+ "/usuario";

	@Autowired
	private RestTemplate restTemplate;
	
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
			throw new MfServiceSecurityException(e);
		}
		return personaDto;
	}
	
	public UsuarioDto registrarUsuario(UsuarioDto usuarioDto) throws MfServiceSecurityException{
		try {
			UtilMfDto.pintaLog(usuarioDto, "usuarioDto");
			
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<UsuarioDto> requestEntity = new HttpEntity<>(usuarioDto, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_2;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);

			log.info(respuesta.toString());

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e);
		}
		return new UsuarioDto();
	}
	
	private URI obtenerUri(String cadenaUrl) throws MfServiceSecurityException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e);
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
