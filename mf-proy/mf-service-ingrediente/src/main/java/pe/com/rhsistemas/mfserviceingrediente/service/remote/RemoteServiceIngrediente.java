/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.remote;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteExportDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceIngrediente {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceIngrediente.class);

	private static final String PATH_SERVICE = "http://mf-jpa-ingrediente";

	private static final String PATH_CONTROLLER_1 = "/IngredienteRJPAService";

	private static final String PATH_CONTROLLER_2 = "/PlatoIngredienteRJPAService";

	private static final String URL_SERVICE_1 = PATH_SERVICE + PATH_CONTROLLER_1 + "/ingredientesPlato";

	private static final String URL_SERVICE_2 = PATH_SERVICE + PATH_CONTROLLER_2 + "/ingredientes";

	private static final String URL_SERVICE_3 = PATH_SERVICE + PATH_CONTROLLER_1 + "/ingredientes";

	private static final String URL_SERVICE_4 = PATH_SERVICE + PATH_CONTROLLER_2 + "/ingredientesMenu";

	private static final String URL_SERVICE_5 = PATH_SERVICE + PATH_CONTROLLER_2 + "/platoIngredientes";

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	public List<PlatoIngredienteDto> ingredientesPlato(Integer idPlato) throws MfServiceIngredienteException {
		List<PlatoIngredienteDto> listaSalida = null;

		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<?> requestEntity = new HttpEntity<>(obtenerHeaders());
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_1 + "/" + idPlato;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

			if (UtilMfDto.listaNoVacia(datosLista)) {
				listaSalida = new ArrayList<>();
				ObjectMapper mapper = obtenerMapper();
				for (Object objeto : datosLista) {
					LinkedHashMap map1 = (LinkedHashMap) objeto;

					listaSalida.add(mapper.convertValue(map1, PlatoIngredienteDto.class));
				}
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
		return listaSalida;
	}

	@SuppressWarnings("rawtypes")
	public void registrarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientes)
			throws MfServiceIngredienteException {
		try {
			HttpMethod metodoServicio = HttpMethod.POST;

			HttpEntity<List<PlatoIngredienteDto>> requestEntity = new HttpEntity<List<PlatoIngredienteDto>>(
					listaIngredientes, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_2;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			log.info(respuesta.toString());

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<IngredienteDto> listarIngredientes() throws MfServiceIngredienteException {
		List<IngredienteDto> listaSalida = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<List<PlatoIngredienteDto>> requestEntity = new HttpEntity<>(null, obtenerHeaders());

			Class<Map> responseType = Map.class;
			String url = URL_SERVICE_3;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

			if (UtilMfDto.listaNoVacia(datosLista)) {
				ObjectMapper mapper = obtenerMapper();
				listaSalida = new ArrayList<>();
				for (Object objeto : datosLista) {
					LinkedHashMap map1 = (LinkedHashMap) objeto;

					listaSalida.add(mapper.convertValue(map1, IngredienteDto.class));
				}
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
		return listaSalida;
	}

	@SuppressWarnings("rawtypes")
	public List<PlatoIngredienteExportDto> listarPlatoIngredientesMenu(Long idMenu)
			throws MfServiceIngredienteException {
		List<PlatoIngredienteExportDto> listaSalida = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<List<PlatoIngredienteDto>> requestEntity = new HttpEntity<>(null, obtenerHeaders());

			Class<Map> responseType = Map.class;
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_5);
			String url = builderURI.toUriString() + "/" + idMenu;

			log.info(url);
			ResponseEntity<Map> respuesta = restTemplate.exchange(url, metodoServicio,
					requestEntity, responseType);

			List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

			if (UtilMfDto.listaNoVacia(datosLista)) {
				ObjectMapper mapper = obtenerMapper();
				listaSalida = new ArrayList<>();
				int i = 1;
				for (Object objeto : datosLista) {
					LinkedHashMap map1 = (LinkedHashMap) objeto;

					PlatoIngredienteExportDto bean = mapper.convertValue(map1, PlatoIngredienteExportDto.class);
					bean.setNroIngrediente(i+"");
					bean.setDescripcionIngrediente(bean.getIngrediente().getNombreIngrediente());
					bean.setCantidadIngrediente(bean.getTotalIngrediente().toString());
					bean.setDescripcionUnidadMedida(bean.getUnidadMedida().getNombre());
					listaSalida.add(bean);
					i++;
				}
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceIngredienteException(e);
		}
		return listaSalida;
	}

	@SuppressWarnings("rawtypes")
	public List<PlatoIngredienteDto> listarIngredientesMenu(Integer idMenu) throws MfServiceIngredienteException {
		List<PlatoIngredienteDto> listaSalida = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpEntity<List<PlatoIngredienteDto>> requestEntity = new HttpEntity<>(null, obtenerHeaders());

			Class<Map> responseType = Map.class;
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_4);
			String url = builderURI.toUriString() + "/" + idMenu;

			log.info(url);

			ResponseEntity<Map> respuesta = restTemplate.exchange(url, metodoServicio, requestEntity, responseType);

			List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

			if (UtilMfDto.listaNoVacia(datosLista)) {
				ObjectMapper mapper = obtenerMapper();
				listaSalida = new ArrayList<>();
				for (Object objeto : datosLista) {
					LinkedHashMap map1 = (LinkedHashMap) objeto;

					listaSalida.add(mapper.convertValue(map1, PlatoIngredienteDto.class));
				}
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
