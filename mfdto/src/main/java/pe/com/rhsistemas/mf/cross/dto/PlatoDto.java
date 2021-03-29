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

	private int id;
	private String nombrePlato;
	private int idEstado;
	private PersonaDto persona;
	private int idTipoPlato;
	private boolean acompaniamiento;
	private BaseValor tipoCocina;
	
	private List<PlatoIngredienteDto> ingredientes;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	public int getIdEstado() {
		return idEstado;
	}
	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	/**
	 * @return the persona
	 */
	public PersonaDto getPersona() {
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
	public int getIdTipoPlato() {
		return idTipoPlato;
	}
	/**
	 * @param idTipoPlato the idTipoPlato to set
	 */
	public void setIdTipoPlato(int idTipoPlato) {
		this.idTipoPlato = idTipoPlato;
	}
	/**
	 * @return the acompaniamiento
	 */
	public boolean isAcompaniamiento() {
		return acompaniamiento;
	}
	/**
	 * @param acompaniamiento the acompaniamiento to set
	 */
	public void setAcompaniamiento(boolean acompaniamiento) {
		this.acompaniamiento = acompaniamiento;
	}
	/**
	 * @return the tipoCocina
	 */
	public BaseValor getTipoCocina() {
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
	
}
