/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceMenu {

	private static final Logger log = LoggerFactory.getLogger(RemoteServiceMenu.class);

	private static final String URL_SERVICE = "http://mf-jpa-menu/MenuRJPAService/menuGenerado";

	private static final String URL_SERVICE_2 = "http://mf-jpa-menu/MenuDetalleRJPAService/menuDetalle";
	
	private static final String URL_SERVICE_3 = "http://mf-jpa-menu/MenuDetalleRJPAService/platoMenuDetalle";

	@Autowired
	private RestTemplate restTemplate;


	@SuppressWarnings("rawtypes")
	public List<MenuGeneradoDto> ultimoMenu(Integer idPersona) throws MfServiceMenuException {
		List<MenuGeneradoDto> listaMenus = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			Map<String, Object> map = new HashMap<>();
			map.put("idPersona", idPersona);

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(map, generarHttpHeaders());
			
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE + "/" + idPersona;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			HttpStatus codigoStatus = respuesta.getStatusCode();
			
			if (!HttpStatus.NO_CONTENT.equals(codigoStatus)) {
				List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
				
				ObjectMapper mapper = obtenerMapper();
				
				listaMenus = new ArrayList<>();
				
				for (Object objeto : datosLista) {
					LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) objeto;
					listaMenus.add(mapper.convertValue(map2, MenuGeneradoDto.class));
				}
			}

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return listaMenus;
	}

	public void grabarMenuGenerado(MenuGeneradoDto menuGeneradoDto) throws MfServiceMenuException {
		try {
			restTemplate.postForEntity(obtenerUri(URL_SERVICE), menuGeneradoDto, Map.class);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
	}
	
	public void grabarMenuDetalleDia(MenuDetalleDto menuDetalleDto) throws MfServiceMenuException {
		try {
			restTemplate.postForEntity(obtenerUri(URL_SERVICE_2), menuDetalleDto, Map.class);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<MenuGeneradoDto> obtenerMenuGeneradoCabecera(Integer idPersona) throws MfServiceMenuException {
		List<MenuGeneradoDto> listaMenu = null;
		HttpMethod metodoServicio = HttpMethod.GET;

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(generarHttpHeaders());
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE + "/" + idPersona;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
		
		HttpStatus codigoStatus = respuesta.getStatusCode();
		
		if (!HttpStatus.NO_CONTENT.equals(codigoStatus)) {
			ObjectMapper mapper = obtenerMapper();
			
			List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			listaMenu = new ArrayList<>();
			for (Object objeto : datosLista) {
				LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) objeto;
				listaMenu.add(mapper.convertValue(map, MenuGeneradoDto.class));
			}
		}

		return listaMenu;
	}

	@SuppressWarnings("rawtypes")
	public Map<Integer,MenuDetalleDto> obtenerMenuGeneradoDetalle(Integer idMenuGenerado) throws MfServiceMenuException {
		Map<Integer,MenuDetalleDto> menuDetalle = null;
		HttpMethod metodoServicio = HttpMethod.GET;

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(generarHttpHeaders());
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE_2 + "/" + idMenuGenerado;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
		
		HttpStatus codigoStatus = respuesta.getStatusCode();
		
		if (!HttpStatus.NO_CONTENT.equals(codigoStatus)) {
			ObjectMapper mapper = obtenerMapper();

			List<?> datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			menuDetalle = new HashMap<Integer,MenuDetalleDto>();
			Calendar cal = Calendar.getInstance();
			for (Object objeto : datosLista) {
				LinkedHashMap map = (LinkedHashMap) objeto;
				MenuDetalleDto objetoDetalle = mapper.convertValue(map, MenuDetalleDto.class);
				cal.setTime(objetoDetalle.getFechaConsumo());
				menuDetalle.put(cal.get(Calendar.DAY_OF_WEEK), objetoDetalle);
			}
		}
		
		return menuDetalle;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public Map<Integer,MenuDetalleDto> cambiarPlatoMenuDia(MenuDetalleDto menudetalleDto) throws MfServiceMenuException{
		HttpMethod metodoServicio = HttpMethod.PUT;
		Map<Integer,MenuDetalleDto> menuDetalle = null;
		
		Class<Map> responseType = Map.class;
		
		RequestCallback callBack = restTemplate.httpEntityCallback(menudetalleDto, responseType);
		
		ResponseExtractor<ResponseEntity<Object>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
		
		ResponseEntity<Object> respuesta = restTemplate.execute(obtenerUri(URL_SERVICE_3), metodoServicio, callBack, responseExtractor);
		
		return menuDetalle;
	}

	private URI obtenerUri(String cadenaUrl) throws MfServiceMenuException {
		URI url = null;
		try {
			url = new URI(cadenaUrl);

		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return url;
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
