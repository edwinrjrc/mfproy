/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

/**
 * @author Edwin
 *
 */
public class PlatoFavoritoPkDto {

	private Integer idPersona;
	private Integer idPlato;
	
	public PlatoFavoritoPkDto() {
	}
	
	public PlatoFavoritoPkDto(Integer idPlato, Integer idPersona) {
		this.idPersona = idPersona;
		this.idPlato = idPlato;
	}
	
	/**
	 * @return the idPersona
	 */
	public Integer getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the idPlato
	 */
	public Integer getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	
}
