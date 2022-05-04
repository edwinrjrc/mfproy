/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.remote;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServiceIngrediente {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteServicePlato.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String URL_SERVICE = "http://mf-jpa-menu/MenuRJPAService/menuGenerado";
	
	
	private HttpHeaders generarHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    return headers;
	}
}
