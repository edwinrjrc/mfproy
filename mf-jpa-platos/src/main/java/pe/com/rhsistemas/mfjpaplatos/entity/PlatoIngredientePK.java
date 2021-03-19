package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the plato_ingrediente database table.
 * 
 */
@Embeddable
public class PlatoIngredientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="plato_id_plato", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer platoIdPlato;

	@Column(name="ingrediente_id_ingrediente", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer ingredienteIdIngrediente;

	public PlatoIngredientePK() {
	}
	public Integer getPlatoIdPlato() {
		return this.platoIdPlato;
	}
	public void setPlatoIdPlato(Integer platoIdPlato) {
		this.platoIdPlato = platoIdPlato;
	}
	public Integer getIngredienteIdIngrediente() {
		return this.ingredienteIdIngrediente;
	}
	public void setIngredienteIdIngrediente(Integer ingredienteIdIngrediente) {
		this.ingredienteIdIngrediente = ingredienteIdIngrediente;
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
			this.platoIdPlato.equals(castOther.platoIdPlato)
			&& this.ingredienteIdIngrediente.equals(castOther.ingredienteIdIngrediente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.platoIdPlato.hashCode();
		hash = hash * prime + this.ingredienteIdIngrediente.hashCode();
		
		return hash;
	}
}