/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service.remote;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mfserreceta.exception.MfServiceRecetaException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceIngrediente {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceIngrediente.class);
	
	private static final String URL_SERVICE_1 = "http://mf-jpa-ingrediente/IngredienteRJPAService/ingredientes";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<IngredienteDto> consultarIngredientes() throws MfServiceRecetaException {
		List<IngredienteDto> listaIngredientes = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;

			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(URL_SERVICE_1), metodoServicio, requestEntity, responseType);

			ObjectMapper mapper = obtenerMapper();

			List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			listaIngredientes = new ArrayList<>();
			for (Object objeto : datosLista) {
				listaIngredientes.add(mapper.convertValue((LinkedHashMap<?, ?>) objeto, IngredienteDto.class));
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
		return listaIngredientes;
	}
	
	private HttpHeaders generarHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
	
	private ObjectMapper obtenerMapper() {
		SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER_FULL);
		df.setTimeZone(TimeZone.getDefault());

		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		ObjectMapper mapper = new ObjectMapper(factory);
		mapper.setDateFormat(df);
		mapper.setTimeZone(TimeZone.getDefault());

		return mapper;
	}
	
	private URI obtenerUri(String cadenaUrl) throws MfServiceRecetaException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);
			
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
		return url;
	}
	
}
