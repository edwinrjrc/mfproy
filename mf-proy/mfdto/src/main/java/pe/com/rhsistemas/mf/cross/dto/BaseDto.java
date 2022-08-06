/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Edwin
 *
 */
public abstract class BaseDto implements Serializable{

	private static final long serialVersionUID = -2452936193817512530L;
	
	/**
	 * Identificador de usuario que registra
	 */
	private Integer idUsuarioRegistro;
	/**
	 * Fecha que se realizo el registro
	 */
	private Timestamp fechaRegistro;
	/**
	 * Identificador de usuario que realizo la modificacion
	 */
	private Integer idUsuarioModificacion;
	/**
	 * Fecha que se realizo la modificacion
	 */
	private Timestamp fechaModificacion;
	
	
	/**
	 * @return the idUsuarioRegistro
	 */
	public Integer getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}
	/**
	 * @param idUsuarioRegistro the idUsuarioRegistro to set
	 */
	public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	/**
	 * @return the fechaRegistro
	 */
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the idUsuarioModificacion
	 */
	public Integer getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}
	/**
	 * @param idUsuarioModificacion the idUsuarioModificacion to set
	 */
	public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
