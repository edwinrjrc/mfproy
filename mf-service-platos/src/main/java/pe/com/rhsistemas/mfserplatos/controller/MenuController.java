/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;
import pe.com.rhsistemas.mfserplatos.service.RemoteServer;

/**
 * @author Edwin
 *
 */
@RestController
@RequestMapping( value = "/mf-service-menu")
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
    private RemoteServer remoteService;
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@GetMapping(value = "/generarMenu")
	public ResponseEntity<Map<String, Object>> generarMenu() throws MfServiceMenuException {
		List<PlatoDto> platos = remoteService.consultarPlatos();
		
		return null;
	}
	
	@GetMapping(value = "/consultarMenu")
	public ResponseEntity<Map<String, Object>> consultarMenu() {
		return null;
	}
}
