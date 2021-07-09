package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the valor_nutricional database table.
 * 
 */
@Embeddable
public class ValorNutricionalPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_ingrediente", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idIngrediente;

	@Column(name="id_tipo_compo", unique=true, nullable=false)
	private Integer idTipoCompo;

	public ValorNutricionalPK() {
	}
	public Integer getIdIngrediente() {
		return this.idIngrediente;
	}
	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	public Integer getIdTipoCompo() {
		return this.idTipoCompo;
	}
	public void setIdTipoCompo(Integer idTipoCompo) {
		this.idTipoCompo = idTipoCompo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ValorNutricionalPK)) {
			return false;
		}
		ValorNutricionalPK castOther = (ValorNutricionalPK)other;
		return 
			this.idIngrediente.equals(castOther.idIngrediente)
			&& this.idTipoCompo.equals(castOther.idTipoCompo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idIngrediente.hashCode();
		hash = hash * prime + this.idTipoCompo.hashCode();
		
		return hash;
	}
}