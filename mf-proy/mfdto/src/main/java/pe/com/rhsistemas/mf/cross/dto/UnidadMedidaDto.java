/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class UnidadMedidaDto extends BaseDto {

	private static final long serialVersionUID = -5555150601289921222L;
	
	private int idUnidadMedida;
	
	private String descripcionUnidadMedida;

	/**
	 * @return the idUnidadMedida
	 */
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}

	/**
	 * @param idUnidadMedida the idUnidadMedida to set
	 */
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	/**
	 * @return the descripcionUnidadMedida
	 */
	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	/**
	 * @param descripcionUnidadMedida the descripcionUnidadMedida to set
	 */
	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}
	

}
