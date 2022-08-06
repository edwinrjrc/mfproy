/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Edwin
 *
 */
public class UsuarioDto extends BaseDto {

	private static final long serialVersionUID = -8805649676053592592L;
	
	private String login;
	private String password;
	private String estado;
	private Timestamp fechaUltActualizaPass;
	private String inCuentaCaduca;
	private Timestamp fechaCaduca;
	private Long idPersona;
	private String nombres;
	private String apellidos;
	private String email;
	private String repitePassword;
	private List<RolDto> listaRoles;
	
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
	public Timestamp getFechaUltActualizaPass() {
		return fechaUltActualizaPass;
	}
	/**
	 * @param fechaUltActualizaPass the fechaUltActualizaPass to set
	 */
	public void setFechaUltActualizaPass(Timestamp fechaUltActualizaPass) {
		this.fechaUltActualizaPass = fechaUltActualizaPass;
	}
	/**
	 * @return the fechaCaduca
	 */
	public Timestamp getFechaCaduca() {
		return fechaCaduca;
	}
	/**
	 * @param fechaCaduca the fechaCaduca to set
	 */
	public void setFechaCaduca(Timestamp fechaCaduca) {
		this.fechaCaduca = fechaCaduca;
	}
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
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
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the repitePassword
	 */
	public String getRepitePassword() {
		return repitePassword;
	}
	/**
	 * @param repitePassword the repitePassword to set
	 */
	public void setRepitePassword(String repitePassword) {
		this.repitePassword = repitePassword;
	}
	/**
	 * @return the listaRoles
	 */
	public List<RolDto> getListaRoles() {
		return listaRoles;
	}
	/**
	 * @param listaRoles the listaRoles to set
	 */
	public void setListaRoles(List<RolDto> listaRoles) {
		this.listaRoles = listaRoles;
	}
	
}
