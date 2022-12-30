/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Edwin
 *
 */
public class UnidadMedidaDto extends BaseDto {

	private static final long serialVersionUID = -5555150601289921222L;
	
	private int idUnidadMedida;
	
	private String descripcionUnidadMedida;
	
	private String descripcionMayuscula;

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

	public String getDescripcionMayuscula() {
		return StringUtils.upperCase(getDescripcionUnidadMedida());
	}

	/**
	 * @param descripcionMayuscula the descripcionMayuscula to set
	 */
	public void setDescripcionMayuscula(String descripcionMayuscula) {
		this.descripcionMayuscula = descripcionMayuscula;
	}
	
}
