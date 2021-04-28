/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
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
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceMenu {

	private static final Logger log = LoggerFactory.getLogger(RemoteServicePlato.class);

	private static final String URL_SERVICE = "http://mf-jpa-menu/MenuRJPAService/menuGenerado";

	private static final String URL_SERVICE_2 = "http://mf-jpa-menu/MenuRJPAService/menuDetalle";

	@Autowired
	private RestTemplate restTemplate;

	public RemoteServiceMenu() {
		// TODO Auto-generated constructor stub
	}

	public List<MenuDetalleDto> ultimoMenu(Integer idPersona) throws MfServiceMenuException {
		List<MenuDetalleDto> listaMenus = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			Map<String, Object> map = new HashMap<>();
			map.put("idPersona", idPersona);

			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(map, headers);
			Class<Map> responseType = Map.class;
			String url = URL_SERVICE + "/" + idPersona;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
					responseType);

			listaMenus = (List<MenuDetalleDto>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return listaMenus;
	}

	public void grabarMenuGenerado(MenuGeneradoDto menuGeneradoDto) throws MfServiceMenuException {
		try {
			Class<Map> responseType = Map.class;

			restTemplate.postForEntity(obtenerUri(URL_SERVICE), menuGeneradoDto, responseType);

		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
	}

	public List<MenuGeneradoDto> obtenerMenuGeneradoCabecera(Integer idPersona) throws MfServiceMenuException {
		MenuGeneradoDto menuDto = null;

		HttpMethod metodoServicio = HttpMethod.GET;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(headers);
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE + "/" + idPersona;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
				responseType);

		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		ObjectMapper mapper = new ObjectMapper(factory);

		List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

		List<MenuGeneradoDto> listaMenu = new ArrayList<>();

		for (Object objeto : datosLista) {
			LinkedHashMap map = (LinkedHashMap) objeto;

			listaMenu.add(mapper.convertValue(map, MenuGeneradoDto.class));
		}

		return listaMenu;
	}

	public List<MenuDetalleDto> obtenerMenuGeneradoDetalle(Integer idMenuGenerado) throws MfServiceMenuException {
		MenuDetalleDto detalleDto = null;

		HttpMethod metodoServicio = HttpMethod.GET;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(headers);
		Class<Map> responseType = Map.class;
		String url = URL_SERVICE_2 + "/" + idMenuGenerado;

		ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity,
				responseType);

		JsonFactory factory = new JsonFactory();
		factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		ObjectMapper mapper = new ObjectMapper(factory);

		List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);

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
