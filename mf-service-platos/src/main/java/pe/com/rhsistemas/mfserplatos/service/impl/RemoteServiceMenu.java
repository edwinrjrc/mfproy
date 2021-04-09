/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
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

import pe.com.rhsistemas.mf.cross.compartido.Constantes;
import pe.com.rhsistemas.mf.cross.dto.MenuDetalleDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceMenu {
	
	private static final Logger logger = LoggerFactory.getLogger(RemoteServicePlato.class);

	private static final String URL_SERVICE = "http://mf-jpa-menu/MenuRJPAService/ultimoMenu";

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
		    
		    Map<String, Object> map= new HashMap<>();
		    map.put("idPersona", idPersona);
		    
			HttpEntity<Map<String,Object>> requestEntity = new HttpEntity<Map<String,Object>>(map,headers);
			Class<Map> responseType = Map.class;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(idPersona.toString()), metodoServicio, requestEntity, responseType);
			
			listaMenus = (List<MenuDetalleDto>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			
		} catch (RestClientException e) {
			logger.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaMenus;
	}

	private URI obtenerUri(String parametro) throws MfServiceMenuException {
		URI url = null;
		try {
			url = new URI(URL_SERVICE+"?idPersona="+parametro);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return url;
	}
	
}
