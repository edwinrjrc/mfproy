/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServer {

	private static final Logger logger = LoggerFactory.getLogger(RemoteServer.class);

	private static final String URL_SERVICE = "http://mf-jpa-platos/PlatoController/consultarPlatos";

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * public void consultarPlatos() { HttpEntity requestEntity = new
	 * HttpEntity(null); ResponseEntity<Map> response =
	 * restTemplate.getForEntity(URL_SERVICE, Map.class);
	 * //ResponseEntity<Map<String, Object>> response = (ResponseEntity<Map<String,
	 * Object>>) restTemplate.exchange(URL_SERVICE, Map.class , String.class);
	 * 
	 * logger.info("Ya se tiene la respuesta");
	 * 
	 * 
	 * int valorStatus = response.getStatusCode().value();
	 * 
	 * logger.info(response.toString()); logger.info("valorStatus ::"+valorStatus);
	 * }
	 */

	public List<PlatoDto> consultarPlatos() throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity requestEntity = new HttpEntity<Map>(headers);
			Class responseType = Map.class;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(), metodoServicio, requestEntity, responseType);
			
			listaPlatos = (List<PlatoDto>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			
			/*RequestCallback requestCallback = new SolicitudCallback(headers);
			ResponseExtractor<Map> responseExtractor = new Extractor<Map>();
			restTemplate.execute(obtenerUri(), metodoServicio, requestCallback, responseExtractor);*/
			
		} catch (RestClientException e) {
			logger.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatos;
	}

	private URI obtenerUri() throws MfServiceMenuException {
		URI url = null;
		try {
			url = new URI(URL_SERVICE);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return url;
	}
}
