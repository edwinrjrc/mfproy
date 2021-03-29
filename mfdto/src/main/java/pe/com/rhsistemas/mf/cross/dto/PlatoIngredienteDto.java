/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class PlatoIngredienteDto extends BaseDto {

	private static final long serialVersionUID = -1582575886598684110L;

	private IngredienteDto ingrediente;
	private BaseValor unidadMedida;
	private double cantidad;
	private BaseValor tipoIngrediente;
	private Integer idPlato;
	
	
	/**
	 * @return the ingrediente
	 */
	public IngredienteDto getIngrediente() {
		return ingrediente;
	}
	/**
	 * @param ingrediente the ingrediente to set
	 */
	public void setIngrediente(IngredienteDto ingrediente) {
		this.ingrediente = ingrediente;
	}
	/**
	 * @return the unidadMedida
	 */
	public BaseValor getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(BaseValor unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the tipoIngrediente
	 */
	public BaseValor getTipoIngrediente() {
		return tipoIngrediente;
	}
	/**
	 * @param tipoIngrediente the tipoIngrediente to set
	 */
	public void setTipoIngrediente(BaseValor tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
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
