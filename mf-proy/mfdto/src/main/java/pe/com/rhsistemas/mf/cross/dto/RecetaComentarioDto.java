/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class RecetaComentarioDto extends BaseDto {

	private static final long serialVersionUID = -1772870992822137125L;
	
	private Integer idPlato;
	
	private Integer idComentarioPlato;
	
	private String comentario;

	public Integer getIdPlato() {
		return idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the idComentarioPlato
	 */
	public Integer getIdComentarioPlato() {
		return idComentarioPlato;
	}

	/**
	 * @param idComentarioPlato the idComentarioPlato to set
	 */
	public void setIdComentarioPlato(Integer idComentarioPlato) {
		this.idComentarioPlato = idComentarioPlato;
	}

	
}
