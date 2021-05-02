/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;

/**
 * @author Edwin
 *
 */
public interface IngredienteLogicaService {

	public List<PlatoIngredienteDto> ingredientesPlato(Integer idPlato) throws MfServiceIngredienteException;
	
	public void registrarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientes) throws MfServiceIngredienteException;
}
