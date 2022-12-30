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
	private Float cantidad;
	private BaseValor tipoIngrediente;
	private Integer idPlato;
	private Integer numeroOrden;
	
	
	/**
	 * @return the ingrediente
	 */
	public IngredienteDto getIngrediente() {
		if (ingrediente == null ) {
			ingrediente = new IngredienteDto();
		}
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
		if (unidadMedida == null) {
			unidadMedida = new BaseValor();
		}
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
	public Float getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the tipoIngrediente
	 */
	public BaseValor getTipoIngrediente() {
		if (tipoIngrediente == null) {
			tipoIngrediente = new BaseValor();
		}
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
	/**
	 * @return the numeroOrden
	 */
	public Integer getNumeroOrden() {
		return numeroOrden;
	}
	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	
}
