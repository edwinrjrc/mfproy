/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
@Service
public class RemoteServicePlato {

	private static final Logger logger = LoggerFactory.getLogger(RemoteServicePlato.class);

	private static final String URL_SERVICE_1 = "http://mf-jpa-platos/PlatoRJPAService/consultarPlatos";
	
	private static final String URL_SERVICE_2 = "http://mf-jpa-platos/PlatoRJPAService/platosNoConsumidos";
	
	private Integer idPersona;
	private Date fechaCorte;

	@Autowired
	private RestTemplate restTemplate;

	public List<PlatoDto> consultarPlatos() throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity requestEntity = new HttpEntity<Map>(headers);
			Class responseType = Map.class;
			
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(1), metodoServicio, requestEntity, responseType);
			
			listaPlatos = (List<PlatoDto>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			
		} catch (RestClientException e) {
			logger.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatos;
	}
	
	public List<PlatoDto> ultimosPlatosConsumidos(Integer idPersona, Date fechaCorte) throws MfServiceMenuException {
		List<PlatoDto> listaPlatos = null;
		try {
			HttpMethod metodoServicio = HttpMethod.GET;
			
			this.idPersona = idPersona;
			this.fechaCorte = fechaCorte;
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity requestEntity = new HttpEntity<Map>(headers);
			Class responseType = Map.class;
			ResponseEntity<Map> respuesta = restTemplate.exchange(obtenerUri(2), metodoServicio, requestEntity, responseType);
			
			listaPlatos = (List<PlatoDto>) respuesta.getBody().get(Constantes.VALOR_DATA_MAP);
			
		} catch (RestClientException e) {
			logger.error(e.getMessage(),e);
			throw new MfServiceMenuException(e);
		}
		return listaPlatos;
	}

	private URI obtenerUri(int u) throws MfServiceMenuException {
		URI url = null;
		try {
			if (u == 1) {
				url = new URI(URL_SERVICE_1);
			}
			else if (u == 2) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				url = new URI(URL_SERVICE_2+"?idPersona="+this.idPersona.toString()+"&fechaRango="+sdf.format(fechaCorte));
			}
			
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new MfServiceMenuException(e);
		}
		return url;
	}
}
