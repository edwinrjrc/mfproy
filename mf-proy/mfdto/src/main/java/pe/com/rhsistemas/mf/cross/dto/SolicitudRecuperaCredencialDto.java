/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.sql.Timestamp;

/**
 * @author Edwin
 *
 */
public class SolicitudRecuperaCredencialDto extends BaseDto {


	private static final long serialVersionUID = -7152238775443812582L;
	
	private Long idSolicitud;
	private Timestamp fechaCaducaSolicitud;
	private Long idPersona;
	private Timestamp fechaSolicitud;
	
	
	/**
	 * @return the idSolicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	/**
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	/**
	 * @return the fechaCaducaSolicitud
	 */
	public Timestamp getFechaCaducaSolicitud() {
		return fechaCaducaSolicitud;
	}
	/**
	 * @param fechaCaducaSolicitud the fechaCaducaSolicitud to set
	 */
	public void setFechaCaducaSolicitud(Timestamp fechaCaducaSolicitud) {
		this.fechaCaducaSolicitud = fechaCaducaSolicitud;
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
	 * @return the fechaSolicitud
	 */
	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	
}
