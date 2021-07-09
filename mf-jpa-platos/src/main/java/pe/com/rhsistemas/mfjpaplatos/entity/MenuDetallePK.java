package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;

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

	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	public MenuDetallePK() {
	}
	public java.util.Date getFeConsumo() {
		return this.feConsumo;
	}
	public void setFeConsumo(java.util.Date feConsumo) {
		this.feConsumo = feConsumo;
	}
	public Integer getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
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
			&& this.idPlato.equals(castOther.idPlato);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feConsumo.hashCode();
		hash = hash * prime + this.idPlato.hashCode();
		
		return hash;
	}
}