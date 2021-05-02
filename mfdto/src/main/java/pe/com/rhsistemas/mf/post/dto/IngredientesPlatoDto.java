/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.PlatoIngredienteDto;

/**
 * @author Edwin
 *
 */
public class IngredientesPlatoDto {

	private int idPlato;
	private List<PlatoIngredienteDto> ingredientes;
	
	
	/**
	 * @return the idPlato
	 */
	public int getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}
	/**
	 * @return the ingredientes
	 */
	public List<PlatoIngredienteDto> getIngredientes() {
		return ingredientes;
	}
	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(List<PlatoIngredienteDto> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
}
