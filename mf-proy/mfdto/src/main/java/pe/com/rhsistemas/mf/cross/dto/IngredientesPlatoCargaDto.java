/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class IngredientesPlatoCargaDto extends BaseDto {

	private static final long serialVersionUID = -2193675213630201689L;

	private String idPlato;
	private String nombrePlato;
	private String nombreIngrediente;
	private String cantidad;
	private String nombreUnidadMedida;
	private String idUsuario;
	
	
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
	 * @return the nombreIngrediente
	 */
	public String getNombreIngrediente() {
		return nombreIngrediente;
	}
	/**
	 * @param nombreIngrediente the nombreIngrediente to set
	 */
	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}
	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the nombreUnidadMedida
	 */
	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}
	/**
	 * @param nombreUnidadMedida the nombreUnidadMedida to set
	 */
	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}
	/**
	 * @return the idPlato
	 */
	public String getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(String idPlato) {
		this.idPlato = idPlato;
	}
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
