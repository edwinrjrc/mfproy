package pe.com.rhsistemas.mfjpaconfiguracion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the conversion_unidad_medida database table.
 * 
 */
@Embeddable
public class ConversionUnidadMedidaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_unid_medi_dest", unique=true, nullable=false)
	private Integer idUnidMediDest;

	@Column(name="id_unid_medi_orig", unique=true, nullable=false)
	private Integer idUnidMediOrig;

	public ConversionUnidadMedidaPK() {
	}
	public Integer getIdUnidMediDest() {
		return this.idUnidMediDest;
	}
	public void setIdUnidMediDest(Integer idUnidMediDest) {
		this.idUnidMediDest = idUnidMediDest;
	}
	public Integer getIdUnidMediOrig() {
		return this.idUnidMediOrig;
	}
	public void setIdUnidMediOrig(Integer idUnidMediOrig) {
		this.idUnidMediOrig = idUnidMediOrig;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConversionUnidadMedidaPK)) {
			return false;
		}
		ConversionUnidadMedidaPK castOther = (ConversionUnidadMedidaPK)other;
		return 
			this.idUnidMediDest.equals(castOther.idUnidMediDest)
			&& this.idUnidMediOrig.equals(castOther.idUnidMediOrig);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUnidMediDest.hashCode();
		hash = hash * prime + this.idUnidMediOrig.hashCode();
		
		return hash;
	}
}