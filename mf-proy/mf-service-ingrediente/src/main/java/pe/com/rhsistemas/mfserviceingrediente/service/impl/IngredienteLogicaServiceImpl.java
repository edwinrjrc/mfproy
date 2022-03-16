/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;
import pe.com.rhsistemas.mfserviceingrediente.service.IngredienteLogicaService;

/**
 * @author Edwin
 *
 */
@Service
public class IngredienteLogicaServiceImpl implements IngredienteLogicaService {

	@Autowired
    private RemoteServiceIngrediente remoteServiceIngrediente;
	
	@Autowired
	private RemoteServiceUnidadMedida remoteServiceUnidadMedida;
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		return restTemplate;
    }
	
	@Override
	public List<PlatoIngredienteDto> ingredientesPlato(Integer idPlato) throws MfServiceIngredienteException{
		List<PlatoIngredienteDto> listaSalida = null;
		
		listaSalida = remoteServiceIngrediente.ingredientesPlato(idPlato);
		
		return listaSalida;
	}

	@Override
	public void registrarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientes)
			throws MfServiceIngredienteException {
		
		remoteServiceIngrediente.registrarIngredientesPlato(listaIngredientes);
		
	}
	
	@Override
	public List<UnidadMedidaDto> listarUnidadesMedida() throws MfServiceIngredienteException {
		List<UnidadMedidaDto> lista = null;
		
		lista = remoteServiceUnidadMedida.listarUnidadesMedida();
		
		return lista;
	}
	
	@Override
	public List<IngredienteDto> listarIngredientes() throws MfServiceIngredienteException {
		List<IngredienteDto> lista = null;
		
		lista = remoteServiceIngrediente.listarIngredientes();
		
		return lista;
	}

}
