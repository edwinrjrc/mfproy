/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service.remote;

import java.util.Arrays;
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
	
	private HttpHeaders generarHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    return headers;
	}
}
