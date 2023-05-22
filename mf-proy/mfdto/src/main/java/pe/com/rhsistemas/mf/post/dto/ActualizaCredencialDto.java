/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

import pe.com.rhsistemas.mf.cross.dto.BaseDto;

/**
 * @author Edwin
 *
 */
public class ActualizaCredencialDto extends BaseDto {

	private static final long serialVersionUID = -9214742724263692362L;

	/**
	 * @see Correo del usuario
	 */
	private String correoUsuario;
	
	/**
	 * @see Credencial nueva
	 */
	private String credencialNueva;

	/**
	 * @return the correoUsuario
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}

	/**
	 * @param correoUsuario the correoUsuario to set
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	/**
	 * @return the credencialNueva
	 */
	public String getCredencialNueva() {
		return credencialNueva;
	}

	/**
	 * @param credencialNueva the credencialNueva to set
	 */
	public void setCredencialNueva(String credencialNueva) {
		this.credencialNueva = credencialNueva;
	}

}
