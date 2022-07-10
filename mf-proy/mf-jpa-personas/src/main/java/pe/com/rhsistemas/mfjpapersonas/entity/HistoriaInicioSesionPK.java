package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the historia_inicio_sesion database table.
 * 
 */
@Embeddable
public class HistoriaInicioSesionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fe_login", unique=true, nullable=false)
	private java.util.Date feLogin;

	@Column(name="id_persona", insertable=false, updatable=false, unique=true, nullable=false)
	private Long idPersona;

	public HistoriaInicioSesionPK() {
	}
	public java.util.Date getFeLogin() {
		return this.feLogin;
	}
	public void setFeLogin(java.util.Date feLogin) {
		this.feLogin = feLogin;
	}
	public Long getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HistoriaInicioSesionPK)) {
			return false;
		}
		HistoriaInicioSesionPK castOther = (HistoriaInicioSesionPK)other;
		return 
			this.feLogin.equals(castOther.feLogin)
			&& this.idPersona.equals(castOther.idPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feLogin.hashCode();
		hash = hash * prime + this.idPersona.hashCode();
		
		return hash;
	}
}