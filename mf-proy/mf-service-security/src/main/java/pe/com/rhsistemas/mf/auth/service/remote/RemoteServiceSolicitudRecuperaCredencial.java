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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.auth.exception.HttpClienteStatusConflict;
import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.SolicitudRecuperaCredencialDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceSolicitudRecuperaCredencial {

	private static final Logger log = LoggerFactory.getLogger(RemoteServicePersona.class);

	private static final String PATH_SERVICE = "http://mf-jpa-personas";

	private static final String PATH_CONTROLLER_1 = "/SolicitudRecuperaCredencialRJPAService";
	
	private static final String URL_SERVICE_1 = PATH_SERVICE + PATH_CONTROLLER_1 + "/solicitudRecuperaCredencial";

	@Autowired
	private RestTemplate restTemplate;
	
	public SolicitudRecuperaCredencialDto registrarSolicitudRecuperaCredencial(SolicitudRecuperaCredencialDto solicitudDto) throws MfServiceSecurityException{
		SolicitudRecuperaCredencialDto salidaSolicitud;
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<SolicitudRecuperaCredencialDto> requestEntity = new HttpEntity<>(solicitudDto, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
		
			ObjectMapper mapper = obtenerMapper();
			
			LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			salidaSolicitud = mapper.convertValue(dato, SolicitudRecuperaCredencialDto.class);

		} catch (HttpClientErrorException.Conflict e) {
			log.error(e.getMessage());
			throw new HttpClienteStatusConflict(e,e.getResponseBodyAsString());
		} catch (RestClientException e) {
			throw new MfServiceSecurityException(e,null);
		}
		return salidaSolicitud;
	}
	
	public SolicitudRecuperaCredencialDto buscarUltimaSolicitudRecuperaCredencial(Long idPersona) throws MfServiceSecurityException{
		SolicitudRecuperaCredencialDto salidaSolicitud;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1 + "/" + idPersona;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
		
			ObjectMapper mapper = obtenerMapper();
			
			LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			salidaSolicitud = mapper.convertValue(dato, SolicitudRecuperaCredencialDto.class);

		} catch (HttpClientErrorException.Conflict e) {
			log.error(e.getMessage());
			throw new HttpClienteStatusConflict(e,e.getResponseBodyAsString());
		} catch (RestClientException e) {
			throw new MfServiceSecurityException(e,null);
		}
		return salidaSolicitud;
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
