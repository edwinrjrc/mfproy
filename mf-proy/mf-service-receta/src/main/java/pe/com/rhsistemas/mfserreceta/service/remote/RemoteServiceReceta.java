/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service.remote;

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
import org.springframework.http.HttpStatus;
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
import pe.com.rhsistemas.mf.cross.dto.IngredientesPlatoCargaDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.exception.MfServiceRecetaException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceReceta {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceReceta.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-receta/RecetaPlatoRJPAService/recetaComentario";

	private static final String URL_SERVICE_2 = "http://mf-jpa-receta/RecetaPlatoRJPAService/recetaComentario";

	private static final String URL_SERVICE_3 = "http://mf-jpa-receta/RecetaPlatoRJPAService/recetaComentario";
	
	private static final String URL_SERVICE_4 = "http://mf-jpa-ingrediente/PlatoIngredienteRJPAService/ingredientes";
	
	private static final String URL_SERVICE_5 = "http://mf-jpa-receta/RecetaPlatoRJPAService/recetaPreparacion";

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	public void guardarComentarioReceta(RecetaComentarioPostDto recetaComentarioPostDto)
			throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<RecetaComentarioPostDto> requestEntity = new HttpEntity<RecetaComentarioPostDto>(
					recetaComentarioPostDto, generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_1);

			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

			log.debug(respuesta.toString());

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public void eliminarComentario(RecetaComentarioPostDto recetaComentarioPostDto) throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.DELETE;

			HttpEntity<RecetaComentarioPostDto> requestEntity = new HttpEntity<RecetaComentarioPostDto>(
					recetaComentarioPostDto, generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_2);

			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

			log.debug(respuesta.toString());

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<RecetaComentarioDto> consultarComentario(Integer idPlato) throws MfServiceRecetaException {
		List<RecetaComentarioDto> listaComentario = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_3);
			builderURI.queryParam("idPlato", idPlato);

			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);
			
			HttpStatus codigoStatus = respuesta.getStatusCode();
			
			if (!HttpStatus.NO_CONTENT.equals(codigoStatus)) {
				ObjectMapper mapper = obtenerMapper();

				List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
				listaComentario = new ArrayList<>();
				for (Object objeto : datosLista) {
					listaComentario.add(mapper.convertValue((LinkedHashMap<?, ?>) objeto, RecetaComentarioDto.class));
				}
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
		return listaComentario;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void guardarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientesCarga) throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity(listaIngredientesCarga,generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_4);

			restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void eliminarIngredientesPlato(Integer idPlato) throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.DELETE;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity(idPlato,generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_4);

			restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void guardarPreparacionPlato(List<RecetaDto> listaPreparacionPlato) throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity(listaPreparacionPlato,generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_5);

			restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void eliminarPreparacionPlato(Integer idPlato) throws MfServiceRecetaException {
		try {
			HttpMethod metodoServicio = HttpMethod.DELETE;

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity(idPlato,generarHttpHeaders());
			Class<Map> responseType = Map.class;

			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_5);

			restTemplate.exchange(builderURI.toUriString(), metodoServicio,
					requestEntity, responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MfServiceRecetaException(e);
		}
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
}
