/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoTipoPlatoDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServicePlato {

	private static final Logger log = LoggerFactory.getLogger(RemoteServicePlato.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-platos/PlatoRJPAService/platos";
	
	private static final String URL_SERVICE_2 = "http://mf-jpa-platos/PlatoRJPAService/platosNoConsumidos";
	
	private static final String URL_SERVICE_3 = "http://mf-jpa-platos/PlatoTipoPlatoRJPAService/tipoPlatoPlato";
	
	@Autowired
	private RestTemplate restTemplate;

	public List<PlatoDto> consultarPlatos() throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(headers);
			Class<Map> responseType = Map.class;
			
			String url = URL_SERVICE_1;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(url), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List<Map> datosLista = (List<Map>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaPlatos = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	LinkedHashMap<String,Object> map = (LinkedHashMap<String,Object>) objeto;
		    	
		    	listaPlatos.add(mapper.convertValue(map, PlatoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatos;
	}
	
	public Map<Integer,PlatoDto> consultarPlatosMap() throws MfServiceMenuException {
		Map<Integer,PlatoDto> mapeoPlatos = new HashMap<Integer,PlatoDto>();
		List<PlatoDto> listaPlatos = consultarPlatos();
		for (PlatoDto platoDto : listaPlatos) {
			mapeoPlatos.put(platoDto.getId(), platoDto);
		}
		
		return mapeoPlatos;
	}
	
	@SuppressWarnings("rawtypes")
	public List<PlatoDto> platosNoConsumidos(Integer idPersona, Date fechaCorteDesde, Date fechaCorteHasta) throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Map> requestEntity = new HttpEntity<Map>(headers);
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_2);
			builderURI.queryParam("idPersona", idPersona);
			builderURI.queryParam("fechaCorteDesde", UtilMfDto.parseDateAString(fechaCorteDesde, ""));
			builderURI.queryParam("fechaCorteHasta", UtilMfDto.parseDateAString(fechaCorteHasta, ""));
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
			
		    List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaPlatos = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	LinkedHashMap map = (LinkedHashMap) objeto;
		    	
		    	listaPlatos.add(mapper.convertValue(map, PlatoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (UtilMfDtoException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatos;
	}
	
	public List<PlatoTipoPlatoDto> consultarTipoPlatoxPlato(Integer idPersona, Date fechaCorteDesde, Date fechaCorteHasta) throws MfServiceMenuException{
		List<PlatoTipoPlatoDto> listaTipoPlato = null;
		
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Map> requestEntity = new HttpEntity<Map>(headers);
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_3);
			builderURI.queryParam("idPersona", idPersona);
			builderURI.queryParam("fechaCorteDesde", UtilMfDto.parseDateAString(fechaCorteDesde, ""));
			builderURI.queryParam("fechaCorteHasta", UtilMfDto.parseDateAString(fechaCorteHasta, ""));
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMAT_DATE_MAPPER);
			JsonFactory factory = new JsonFactory();
		    factory.enable(Feature.ALLOW_SINGLE_QUOTES);
		    ObjectMapper mapper = new ObjectMapper(factory);
		    mapper.setDateFormat(df);
			
		    List<LinkedHashMap<String,Object>> datosLista = (List<LinkedHashMap<String,Object>>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    if (UtilMfDto.listaNoVacia(datosLista)) {
		    	listaTipoPlato = new ArrayList<>();
			    for (LinkedHashMap<String,Object> map : datosLista) {
			    	listaTipoPlato.add(mapper.convertValue(map, PlatoTipoPlatoDto.class));
				}
		    }
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (SecurityException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (UtilMfDtoException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		
		return listaTipoPlato;
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
