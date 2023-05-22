/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

import pe.com.rhsistemas.mf.cross.dto.ValidaCodigoSeguridadDto;

/**
 * @author Edwin
 *
 */
public class ValidaCodigoBeanDto {
	
	private ValidaCodigoSeguridadDto validacionCodigoSeguridad;

	/**
	 * @return the validacionCodigoSeguridad
	 */
	public ValidaCodigoSeguridadDto getValidacionCodigoSeguridad() {
		return validacionCodigoSeguridad;
	}

	/**
	 * @param validacionCodigoSeguridad the validacionCodigoSeguridad to set
	 */
	public void setValidacionCodigoSeguridad(ValidaCodigoSeguridadDto validacionCodigoSeguridad) {
		this.validacionCodigoSeguridad = validacionCodigoSeguridad;
	}

}
