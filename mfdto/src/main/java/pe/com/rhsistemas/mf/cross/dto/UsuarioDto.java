/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.Date;

/**
 * @author Edwin
 *
 */
public class UsuarioDto extends BaseDto {

	private static final long serialVersionUID = -8805649676053592592L;
	
	/**
	 * 
	 */
	private String login;
	private String password;
	private String estado;
	private Date fechaUltActualizaPass;
	private String inCuentaCaduca;
	private Date fechaCaduca;
	private int idPersona;
	
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaUltActualizaPass
	 */
	public Date getFechaUltActualizaPass() {
		return fechaUltActualizaPass;
	}
	/**
	 * @param fechaUltActualizaPass the fechaUltActualizaPass to set
	 */
	public void setFechaUltActualizaPass(Date fechaUltActualizaPass) {
		this.fechaUltActualizaPass = fechaUltActualizaPass;
	}
	/**
	 * @return the fechaCaduca
	 */
	public Date getFechaCaduca() {
		return fechaCaduca;
	}
	/**
	 * @param fechaCaduca the fechaCaduca to set
	 */
	public void setFechaCaduca(Date fechaCaduca) {
		this.fechaCaduca = fechaCaduca;
	}
	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the inCuentaCaduca
	 */
	public String getInCuentaCaduca() {
		return inCuentaCaduca;
	}
	/**
	 * @param inCuentaCaduca the inCuentaCaduca to set
	 */
	public void setInCuentaCaduca(String inCuentaCaduca) {
		this.inCuentaCaduca = inCuentaCaduca;
	}
	
	
}
