/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import pe.com.rhsistemas.mf.post.dto.DatosCorreo;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceMail {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteServiceMail.class);

	private static final String PATH_SERVICE = "http://mf-service-mail";

	private static final String PATH_CONTROLLER_1 = "/correoService";
	
	private static final String URL_SERVICE_1 = PATH_SERVICE + PATH_CONTROLLER_1 + "/correoSinAdjunto";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public boolean enviarCorreo(DatosCorreo datosCorreo) throws MfServiceSecurityException{
		boolean envio = false;
		try {
			HttpMethod metodoServicio = HttpMethod.POST;
			
			HttpEntity<DatosCorreo> requestEntity = new HttpEntity<>(datosCorreo, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			envio = true;
			
		} catch (RestClientException e) {
			log.info(e.getMessage());
			log.error(e.getMessage(), e);
			throw new MfServiceSecurityException(e,e.getMessage());
		}
		return envio;
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
