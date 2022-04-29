/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.exception.MfServiceRecetaException;
import pe.com.rhsistemas.mfserreceta.service.RecetaService;
import pe.com.rhsistemas.mfserreceta.service.remote.RemoteServiceReceta;

/**
 * @author Edwin
 *
 */
@Service
public class RecetaServiceImpl implements RecetaService {

	@Autowired
	private RemoteServiceReceta remoteServiceReceta;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
	}
	
	@Override
	public void guardarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException {
		remoteServiceReceta.guardarComentarioReceta(recetaComentarioDto);
	}
	
	@Override
	public void eliminarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException {
		remoteServiceReceta.eliminarComentario(recetaComentarioDto);
	}
	
	@Override
	public List<RecetaComentarioDto> consultarComentario(Integer idPlato) throws MfServiceRecetaException{
		return remoteServiceReceta.consultarComentario(idPlato);
	}
}
