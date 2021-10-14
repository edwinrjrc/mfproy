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
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;


/**
 * @author Edwin
 *
 */
@Service
public class RemoteServicePlato {

	private static final Logger log = LoggerFactory.getLogger(RemoteServicePlato.class);
	
	private static final String URL_SERVICE_1 = "http://mf-jpa-platos/PlatoFavoritoRJPAService/platoFavorito";
	
	private static final String URL_SERVICE_2 = "http://mf-jpa-platos/PlatoRJPAService/platos";
	
	private static final String URL_SERVICE_3 = "http://mf-jpa-platos/PlatoFavoritoRJPAService/platosFavoritos";
	
	private static final String URL_SERVICE_4 = "http://mf-jpa-platos/PlatoRJPAService/plato";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public void guardarPlatoFavorito(PlatoFavoritoDto platoFavoritoDto) throws MFServicePlatoException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;
			
			HttpEntity<PlatoFavoritoDto> requestEntity = new HttpEntity<PlatoFavoritoDto>(platoFavoritoDto,generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_1);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			log.info(respuesta.toString());
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<PlatoDto> listarPlatos() throws MFServicePlatoException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(URL_SERVICE_2), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaPlatos = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaPlatos.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, PlatoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return listaPlatos;
	}
	
	@SuppressWarnings("rawtypes")
	public List<PlatoFavoritoDto> listarPlatosFavoritos(Integer idPersona) throws MFServicePlatoException{
		List<PlatoFavoritoDto> listaFavoritos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_3);
			builderURI.queryParam("idPersona", idPersona);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaFavoritos = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaFavoritos.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, PlatoFavoritoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return listaFavoritos;
	}
	
	@SuppressWarnings("rawtypes")
	public PlatoDto consultarPlato(Integer idPlato) throws MFServicePlatoException {
		PlatoDto platoDto = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_4);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    LinkedHashMap<?,?> dato = (LinkedHashMap<?,?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    platoDto = mapper.convertValue(dato, PlatoDto.class);
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return platoDto;
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
