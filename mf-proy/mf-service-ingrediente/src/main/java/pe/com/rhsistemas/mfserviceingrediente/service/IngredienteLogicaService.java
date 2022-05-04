/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.service;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.IngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;
import pe.com.rhsistemas.mf.cross.dto.UnidadMedidaDto;
import pe.com.rhsistemas.mfserviceingrediente.exception.MfServiceIngredienteException;

/**
 * @author Edwin
 *
 */
public interface IngredienteLogicaService {

	public List<PlatoIngredienteDto> ingredientesPlato(Integer idPlato) throws MfServiceIngredienteException;
	
	public void registrarIngredientesPlato(List<PlatoIngredienteDto> listaIngredientes) throws MfServiceIngredienteException;

	public List<UnidadMedidaDto> listarUnidadesMedida() throws MfServiceIngredienteException;

	public List<IngredienteDto> listarIngredientes() throws MfServiceIngredienteException;

	List<PlatoIngredienteDto> listarIngredientesMenu(Integer idMenu) throws MfServiceIngredienteException;
}
