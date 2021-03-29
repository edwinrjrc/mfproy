/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Edwin
 *
 */
public abstract class BaseDto implements Serializable{

	private static final long serialVersionUID = -2452936193817512530L;
	
	/**
	 * Identificador de usuario que registra
	 */
	private int idUsuarioRegistro;
	/**
	 * Fecha que se realizo el registro
	 */
	private Date fechaRegistro;
	/**
	 * Identificador de usuario que realizo la modificacion
	 */
	private int idUsuarioModificacion;
	/**
	 * Fecha que se realizo la modificacion
	 */
	private Date fechaModificacion;
	
	
	/**
	 * @return the idUsuarioRegistro
	 */
	public int getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}
	/**
	 * @param idUsuarioRegistro the idUsuarioRegistro to set
	 */
	public void setIdUsuarioRegistro(int idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the idUsuarioModificacion
	 */
	public int getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}
	/**
	 * @param idUsuarioModificacion the idUsuarioModificacion to set
	 */
	public void setIdUsuarioModificacion(int idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	

}
