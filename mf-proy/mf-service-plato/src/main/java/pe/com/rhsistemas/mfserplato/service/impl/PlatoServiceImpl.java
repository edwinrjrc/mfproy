/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service.impl;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;
import pe.com.rhsistemas.mfserplato.service.PlatoService;
import pe.com.rhsistemas.mfserplato.service.remote.RemoteServicePlato;

/**
 * @author Edwin
 *
 */
@Service
public class PlatoServiceImpl implements PlatoService {
	
	@Autowired
	private RemoteServicePlato remoteServicePlato;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
	}
	
	@Override
	public void guardarPlatoFavorito(PlatoFavoritoDto platoFavoritoDto) throws MFServicePlatoException {
		remoteServicePlato.guardarPlatoFavorito(platoFavoritoDto);
	}

}
