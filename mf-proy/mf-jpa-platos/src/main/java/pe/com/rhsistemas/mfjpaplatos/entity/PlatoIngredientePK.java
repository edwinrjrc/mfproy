package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plato_ingrediente database table.
 * 
 */
@Embeddable
public class PlatoIngredientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="id_ingrediente", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idIngrediente;

	public PlatoIngredientePK() {
	}
	public Integer getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	public Integer getIdIngrediente() {
		return this.idIngrediente;
	}
	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlatoIngredientePK)) {
			return false;
		}
		PlatoIngredientePK castOther = (PlatoIngredientePK)other;
		return 
			this.idPlato.equals(castOther.idPlato)
			&& this.idIngrediente.equals(castOther.idIngrediente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPlato.hashCode();
		hash = hash * prime + this.idIngrediente.hashCode();
		
		return hash;
	}
}