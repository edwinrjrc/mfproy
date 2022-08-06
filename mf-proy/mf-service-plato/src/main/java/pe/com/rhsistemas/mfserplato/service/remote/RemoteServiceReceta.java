/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service.remote;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceReceta {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceReceta.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-ingrediente/IngredienteRJPAService/ingredientesPlato";
	
	private static final String URL_SERVICE_2 = "http://mf-jpa-receta/RecetaPlatoRJPAService/receta";
	
	private static final String URL_SERVICE_3 = "http://mf-jpa-receta/RecetaPlatoRJPAService/recetaComentario";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	public List<PlatoIngredienteDto> listarIngredientesPlato(Integer idPlato) throws MFServicePlatoException{
		List<PlatoIngredienteDto> listaIngredientesPlato = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_1);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
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
	
	@SuppressWarnings("rawtypes")
	public List<RecetaDto> consultaRecetaPlato(Integer idPlato) throws MFServicePlatoException{
		List<RecetaDto> listaReceta = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_2);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
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
	
	@SuppressWarnings("rawtypes")
	public List<RecetaComentarioDto> consultaComentariosReceta(Integer idPlato) throws MFServicePlatoException{
		List<RecetaComentarioDto> listaComentario = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_3);
			builderURI.queryParam("idPlato", idPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaComentario = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaComentario.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, RecetaComentarioDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MFServicePlatoException(e);
		}
		return listaComentario;
	}
	
	private HttpHeaders generarHttpHeaders() {
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
