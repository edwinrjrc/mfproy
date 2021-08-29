/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class PlatoFavoritoDto extends BaseDto {

	private static final long serialVersionUID = -5550730272640845149L;

	private Integer idPlato;
	
	private Long idPersona;
	
	private String estadoPlatoFavorito;

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

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the estadoPlatoFavorito
	 */
	public String getEstadoPlatoFavorito() {
		return estadoPlatoFavorito;
	}

	/**
	 * @param estadoPlatoFavorito the estadoPlatoFavorito to set
	 */
	public void setEstadoPlatoFavorito(String estadoPlatoFavorito) {
		this.estadoPlatoFavorito = estadoPlatoFavorito;
	}
	
	
}
