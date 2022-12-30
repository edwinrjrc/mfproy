/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import pe.com.rhsistemas.mf.cross.dto.PlatoTipoPlatoDto;
import pe.com.rhsistemas.mf.cross.dto.TipoPlatoDto;
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
	
	private static final String URL_SERVICE_4 = "http://mf-jpa-platos/TipoPlatoRJPAService/tiposPlatoFondo";
	
	private static final String URL_SERVICE_5 = "http://mf-jpa-platos/PlatoRJPAService/platosNoConsumidosTipo";
	
	private static final String URL_SERVICE_6 = "http://mf-jpa-platos/PlatoFavoritoRJPAService/platoFavorito";
	
	private static final String URL_SERVICE_7 = "http://mf-jpa-platos/PlatoFavoritoRJPAService/platoNombre";
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	public List<PlatoDto> consultarPlatos() throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(URL_SERVICE_1), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
		    List datosLista = (List) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaPlatos = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	LinkedHashMap map = (LinkedHashMap) objeto;
		    	
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
			
			HttpEntity<Map> requestEntity = new HttpEntity<Map>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_2);
			builderURI.queryParam("idPersona", idPersona);
			builderURI.queryParam("fechaCorteDesde", UtilMfDto.parseDateAString(fechaCorteDesde, ""));
			builderURI.queryParam("fechaCorteHasta", UtilMfDto.parseDateAString(fechaCorteHasta, ""));
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
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
	
	@SuppressWarnings("rawtypes")
	public List<PlatoDto> platosNoConsumidosTipo(Integer idPersona, Date fechaCorteDesde, Date fechaCorteHasta, Integer idTipoPlato) throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map> requestEntity = new HttpEntity<Map>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_5);
			builderURI.queryParam("idPersona", idPersona);
			builderURI.queryParam("fechaCorteDesde", UtilMfDto.parseDateAString(fechaCorteDesde, ""));
			builderURI.queryParam("fechaCorteHasta", UtilMfDto.parseDateAString(fechaCorteHasta, ""));
			builderURI.queryParam("idTipoPlato", idTipoPlato);
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PlatoTipoPlatoDto> consultarTipoPlatoxPlato(Integer idPersona, Date fechaCorteDesde, Date fechaCorteHasta) throws MfServiceMenuException{
		List<PlatoTipoPlatoDto> listaTipoPlato = null;
		
		try {
			log.info("Recibiendo parametros");
			UtilMfDto.pintaLog(idPersona, "idPersona");
			UtilMfDto.pintaLog(fechaCorteDesde, "fechaCorteDesde");
			UtilMfDto.pintaLog(fechaCorteHasta, "fechaCorteHasta");
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map> requestEntity = new HttpEntity<Map>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_3);
			builderURI.queryParam("idPersona", idPersona);
			builderURI.queryParam("fechaCorteDesde", UtilMfDto.parseDateAString(fechaCorteDesde, ""));
			builderURI.queryParam("fechaCorteHasta", UtilMfDto.parseDateAString(fechaCorteHasta, ""));
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
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
	
	@SuppressWarnings("rawtypes")
	public List<TipoPlatoDto> consultarTiposPlatoPlatos() throws MfServiceMenuException {
		List<TipoPlatoDto> listaTipoPlato = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(URL_SERVICE_4), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaTipoPlato = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaTipoPlato.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, TipoPlatoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaTipoPlato;
	}
	
	@SuppressWarnings("rawtypes")
	public List<PlatoFavoritoDto> consultarPlatoFavorito(String listaPlatos, Integer idPersona) throws MfServiceMenuException {
		List<PlatoFavoritoDto> listaPlatoFavorito = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String,Object>>(generarHttpHeaders());
			Class<Map> responseType = Map.class;
			
			UriComponentsBuilder builderURI = UriComponentsBuilder.fromHttpUrl(URL_SERVICE_6);
			builderURI.queryParam("listaPlatoFavorito", listaPlatos);
			builderURI.queryParam("idPersona", idPersona.toString());
			
			log.info("valor json :: "+new String(builderURI.toUriString().getBytes(),StandardCharsets.UTF_8));
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(builderURI.toUriString(), metodoServicio, requestEntity, responseType);
			
			ObjectMapper mapper = obtenerMapper();
			
		    List<?> datosLista = (List<?>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
		    listaPlatoFavorito = new ArrayList<>();
		    for (Object objeto : datosLista) {
		    	listaPlatoFavorito.add(mapper.convertValue((LinkedHashMap<?,?>) objeto, PlatoFavoritoDto.class));
			}
			
		} catch (RestClientException e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatoFavorito;
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
