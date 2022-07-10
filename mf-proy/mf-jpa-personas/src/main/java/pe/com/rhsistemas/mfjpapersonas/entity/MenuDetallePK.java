package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the menu_detalle database table.
 * 
 */
@Embeddable
public class MenuDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fe_consumo", unique=true, nullable=false)
	private java.util.Date feConsumo;

	@Column(name="id_generado", unique=true, nullable=false)
	private Long idGenerado;

	public MenuDetallePK() {
	}
	public java.util.Date getFeConsumo() {
		return this.feConsumo;
	}
	public void setFeConsumo(java.util.Date feConsumo) {
		this.feConsumo = feConsumo;
	}
	public Long getIdGenerado() {
		return this.idGenerado;
	}
	public void setIdGenerado(Long idGenerado) {
		this.idGenerado = idGenerado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MenuDetallePK)) {
			return false;
		}
		MenuDetallePK castOther = (MenuDetallePK)other;
		return 
			this.feConsumo.equals(castOther.feConsumo)
			&& this.idGenerado.equals(castOther.idGenerado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feConsumo.hashCode();
		hash = hash * prime + this.idGenerado.hashCode();
		
		return hash;
	}
}