package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the receta database table.
 * 
 */
@Embeddable
public class RecetaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_paso", unique=true, nullable=false)
	private Integer idPaso;

	@Column(name="id_plato", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="id_ingrediente", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idIngrediente;

	public RecetaPK() {
	}
	public Integer getIdPaso() {
		return this.idPaso;
	}
	public void setIdPaso(Integer idPaso) {
		this.idPaso = idPaso;
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
		if (!(other instanceof RecetaPK)) {
			return false;
		}
		RecetaPK castOther = (RecetaPK)other;
		return 
			this.idPaso.equals(castOther.idPaso)
			&& this.idPlato.equals(castOther.idPlato)
			&& this.idIngrediente.equals(castOther.idIngrediente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPaso.hashCode();
		hash = hash * prime + this.idPlato.hashCode();
		hash = hash * prime + this.idIngrediente.hashCode();
		
		return hash;
	}
}