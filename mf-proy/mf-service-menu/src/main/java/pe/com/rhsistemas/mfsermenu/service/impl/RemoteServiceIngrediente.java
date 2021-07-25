/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
