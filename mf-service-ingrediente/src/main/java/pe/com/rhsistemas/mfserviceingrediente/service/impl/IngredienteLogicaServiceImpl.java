/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
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
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
		
		
		
		
	}

}
