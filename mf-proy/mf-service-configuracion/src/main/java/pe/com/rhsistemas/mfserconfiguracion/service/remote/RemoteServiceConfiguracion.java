/**
 * 
 */
package pe.com.rhsistemas.mfserconfiguracion.service.remote;

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

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mfserconfiguracion.exception.MfServiceConfiguracionException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceConfiguracion {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteServiceConfiguracion.class);
	
	private static final String URL_SERVICE_1 = "http://mf-jpa-configuracion/ConfiguracionFamiliaRJPAService/configuracionFamilia";

	@Autowired
	private RestTemplate restTemplate;
	
	public void guardarConfiguracionCuenta(ConfiguracionCuentaDto configuracionCuentaDto) throws MfServiceConfiguracionException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<?> requestEntity = new HttpEntity<>(configuracionCuentaDto,obtenerHeaders());
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceConfiguracionException(e);
		}
	}
	
	public ConfiguracionCuentaDto consultarConfiguracionCuenta(Integer idPersona) throws MfServiceConfiguracionException {
		ConfiguracionCuentaDto configuracion = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<?> requestEntity = new HttpEntity<>(obtenerHeaders());
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1;
			url = URL_SERVICE_1 + "/" + idPersona;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			ObjectMapper mapper = obtenerMapper();
			Map objetoResultado = respuesta.getBody();
			
			configuracion = mapper.convertValue(objetoResultado.get(Constantes.VALOR_DATA_MAP), ConfiguracionCuentaDto.class);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceConfiguracionException(e);
		}
		return configuracion;
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
	
	private URI obtenerUri(String cadenaUrl) throws MfServiceConfiguracionException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceConfiguracionException(e);
		}
		return url;
	}

}
