/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

	private static final Logger log = LoggerFactory.getLogger(RemoteServicePlato.class);

	private static final String URL_SERVICE = "http://mf-jpa-menu/MenuRJPAService/menuGenerado";

	private static final String URL_SERVICE_2 = "http://mf-jpa-menu/MenuDetalleRJPAService/menuDetalle";

	@Autowired
	private RestTemplate restTemplate;


	@SuppressWarnings("rawtypes")
	public List<MenuGeneradoDto> ultimoMenu(Integer idPersona) throws MfServiceMenuException {
		List<MenuGeneradoDto> listaMenus = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			Map<String, Object> map = new HashMap<>();
			map.put("idPersona", idPersona);

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(map, headers);
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE + "/" + idPersona;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);

			List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			
			SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER);
			
			JsonFactory factory = new JsonFactory();
			factory.enable(Feature.ALLOW_SINGLE_QUOTES);
			
			ObjectMapper mapper = new ObjectMapper(factory);
			
			mapper.setDateFormat(df);
			
			listaMenus = new ArrayList<>();
			
			for (Object objeto : datosLista) {
				LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) objeto;
				listaMenus.add(mapper.convertValue(map2, MenuGeneradoDto.class));
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

	@SuppressWarnings("rawtypes")
	public List<MenuGeneradoDto> obtenerMenuGeneradoCabecera(Integer idPersona) throws MfServiceMenuException {
		HttpMethod metodoServicio = HttpMethod.GET;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(headers);
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE + "/" + idPersona;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);

		SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER);
		
		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		ObjectMapper mapper = new ObjectMapper(factory);
		mapper.setDateFormat(df);

		List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

		List<MenuGeneradoDto> listaMenu = new ArrayList<>();

		for (Object objeto : datosLista) {
			LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) objeto;
			listaMenu.add(mapper.convertValue(map, MenuGeneradoDto.class));
		}

		return listaMenu;
	}

	@SuppressWarnings("rawtypes")
	public List<MenuDetalleDto> obtenerMenuGeneradoDetalle(Integer idMenuGenerado) throws MfServiceMenuException {
		HttpMethod metodoServicio = HttpMethod.GET;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(headers);
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE_2 + "/" + idMenuGenerado;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
		
		SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER);

		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		ObjectMapper mapper = new ObjectMapper(factory);
		mapper.setDateFormat(df);

		List<?> datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

		List<MenuDetalleDto> listaMenuDetalle = new ArrayList<>();

		for (Object objeto : datosLista) {
			LinkedHashMap map = (LinkedHashMap) objeto;
			listaMenuDetalle.add(mapper.convertValue(map, MenuDetalleDto.class));
		}

		return listaMenuDetalle;
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

}
