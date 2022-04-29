/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.BaseDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;

/**
 * @author Edwin
 *
 */
public class RecetaModificadaDto extends BaseDto {

	private static final long serialVersionUID = 1336423602313087654L;
	
	private List<RecetaDto> listaReceta;

	/**
	 * @return the listaReceta
	 */
	public List<RecetaDto> getListaReceta() {
		return listaReceta;
	}

	/**
	 * @param listaReceta the listaReceta to set
	 */
	public void setListaReceta(List<RecetaDto> listaReceta) {
		this.listaReceta = listaReceta;
	}

	
}
