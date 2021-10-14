/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceReceta {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceReceta.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-ingrediente/IngredienteRJPAService/ingredientes";
	
	private static final String URL_SERVICE_2 = "http://mf-jpa-receta/RecetaPlatoRJPAService/receta";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<PlatoIngredienteDto> listarIngredientesPlato(Integer idPlato) throws MFServicePlatoException{
		List<PlatoIngredienteDto> listaIngredientesPlato = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_1);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaIngredientesPlato = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaIngredientesPlato.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, PlatoIngredienteDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return listaIngredientesPlato;
	}
	
	public List<RecetaDto> consultaRecetaPlato(Integer idPlato) throws MFServicePlatoException{
		List<RecetaDto> listaReceta = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_2);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaReceta = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaReceta.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, RecetaDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return listaReceta;
	}
	
	private URI obtenerUri(String cadenaUrl) throws MFServicePlatoException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MFServicePlatoException(e);
		}
		return url;
	}
	
	private HttpHeaders generarHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    return headers;
	}
	
}
