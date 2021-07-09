package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plato_tipo_plato database table.
 * 
 */
@Embeddable
public class PlatoTipoPlatoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="id_tipo_plat", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idTipoPlat;

	public PlatoTipoPlatoPK() {
	}
	public Integer getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	public Integer getIdTipoPlat() {
		return this.idTipoPlat;
	}
	public void setIdTipoPlat(Integer idTipoPlat) {
		this.idTipoPlat = idTipoPlat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlatoTipoPlatoPK)) {
			return false;
		}
		PlatoTipoPlatoPK castOther = (PlatoTipoPlatoPK)other;
		return 
			this.idPlato.equals(castOther.idPlato)
			&& this.idTipoPlat.equals(castOther.idTipoPlat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPlato.hashCode();
		hash = hash * prime + this.idTipoPlat.hashCode();
		
		return hash;
	}
}