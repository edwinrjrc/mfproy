/**
 * 
 */
package pe.com.rhsistemas.mfserconfiguracion.service.impl;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mfserconfiguracion.exception.MfServiceConfiguracionException;
import pe.com.rhsistemas.mfserconfiguracion.service.ConfiguracionCuentaService;
import pe.com.rhsistemas.mfserconfiguracion.service.remote.RemoteServiceConfiguracion;

/**
 * @author Edwin
 *
 */
@Service
public class ConfiguracionCuentaServiceImpl implements ConfiguracionCuentaService {
	
	private static final Logger log = LoggerFactory.getLogger(ConfiguracionCuentaServiceImpl.class);

	@Autowired
	private RemoteServiceConfiguracion remoteServiceConfiguracion;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		return restTemplate;
	}
	
	@Override
	public void guardaConfiguracionCuenta(ConfiguracionCuentaDto configuracion) throws MfServiceConfiguracionException {
		remoteServiceConfiguracion.guardarConfiguracionCuenta(configuracion);
	}
	
	@Override
	public ConfiguracionCuentaDto consultarConfiguracionFamilia(Integer idPersona) throws MfServiceConfiguracionException {
		return remoteServiceConfiguracion.consultarConfiguracionCuenta(idPersona);
	}

}
