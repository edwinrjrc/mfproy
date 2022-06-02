/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class PlatoDto extends BaseDto {

	private static final long serialVersionUID = 8401732990926088650L;

	private Integer id;
	private String nombrePlato;
	private Integer idEstado;
	private PersonaDto persona;
	private Integer idTipoPlato;
	private Boolean acompaniamiento;
	private BaseValor tipoCocina;
	private String descripcionPlato;
	
	private List<PlatoIngredienteDto> ingredientes;
	
	private Boolean favorito;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombrePlato
	 */
	public String getNombrePlato() {
		return nombrePlato;
	}

	/**
	 * @param nombrePlato the nombrePlato to set
	 */
	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}

	/**
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the persona
	 */
	public PersonaDto getPersona() {
		if (persona == null) {
			persona = new PersonaDto();
		}
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}

	/**
	 * @return the idTipoPlato
	 */
	public Integer getIdTipoPlato() {
		return idTipoPlato;
	}

	/**
	 * @param idTipoPlato the idTipoPlato to set
	 */
	public void setIdTipoPlato(Integer idTipoPlato) {
		this.idTipoPlato = idTipoPlato;
	}

	/**
	 * @return the acompaniamiento
	 */
	public Boolean isAcompaniamiento() {
		return acompaniamiento;
	}

	/**
	 * @param acompaniamiento the acompaniamiento to set
	 */
	public void setAcompaniamiento(Boolean acompaniamiento) {
		this.acompaniamiento = acompaniamiento;
	}

	/**
	 * @return the tipoCocina
	 */
	public BaseValor getTipoCocina() {
		if (tipoCocina == null) {
			tipoCocina = new BaseValor();
		}
		return tipoCocina;
	}

	/**
	 * @param tipoCocina the tipoCocina to set
	 */
	public void setTipoCocina(BaseValor tipoCocina) {
		this.tipoCocina = tipoCocina;
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

	/**
	 * @return the favorito
	 */
	public Boolean isFavorito() {
		return favorito;
	}

	/**
	 * @param favorito the favorito to set
	 */
	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	/**
	 * @return the descripcionPlato
	 */
	public String getDescripcionPlato() {
		return descripcionPlato;
	}

	/**
	 * @param descripcionPlato the descripcionPlato to set
	 */
	public void setDescripcionPlato(String descripcionPlato) {
		this.descripcionPlato = descripcionPlato;
	}
	
}
