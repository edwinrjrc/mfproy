package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plato_favorito database table.
 * 
 */
@Embeddable
public class PlatoFavoritoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	public PlatoFavoritoPK() {
	}
	public Long getIdPersona() {
		return this.idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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
		if (!(other instanceof PlatoFavoritoPK)) {
			return false;
		}
		PlatoFavoritoPK castOther = (PlatoFavoritoPK)other;
		return 
			this.idPersona.equals(castOther.idPersona)
			&& this.idPlato.equals(castOther.idPlato);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPersona.hashCode();
		hash = hash * prime + this.idPlato.hashCode();
		
		return hash;
	}
}