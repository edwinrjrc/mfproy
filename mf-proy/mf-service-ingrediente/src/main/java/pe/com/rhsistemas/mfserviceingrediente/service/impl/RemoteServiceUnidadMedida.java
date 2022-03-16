/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.impl;

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
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceUnidadMedida {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceUnidadMedida.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-ingrediente/UnidadMedidaRJPAService/UnidadesMedida";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<UnidadMedidaDto> listarUnidadesMedida() throws MfServiceIngredienteException {
		List<UnidadMedidaDto> listaSalida = null;

		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<?> requestEntity = new HttpEntity<>(obtenerHeaders());
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			ObjectMapper mapper = obtenerMapper();

			List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

			listaSalida = new ArrayList<>();
			for (Object objeto : datosLista) {
				LinkedHashMap map1 = (LinkedHashMap) objeto;

				listaSalida.add(mapper.convertValue(map1, UnidadMedidaDto.class));
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
		return listaSalida;
	}
	
	private URI obtenerUri(String cadenaUrl) throws MfServiceIngredienteException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
		return url;
	}
	
	
	private HttpHeaders obtenerHeaders() {
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
	
}
