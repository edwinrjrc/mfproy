package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the menu_detalle database table.
 * 
 */
@Embeddable
public class MenuDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="fe_consumo", unique=true, nullable=false)
	private Timestamp feConsumo;

	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="id_generado", unique=true, nullable=false)
	private Long idGenerado;

	public MenuDetallePK() {
	}
	public Timestamp getFeConsumo() {
		return this.feConsumo;
	}
	public void setFeConsumo(Timestamp feConsumo) {
		this.feConsumo = feConsumo;
	}
	public Integer getIdPlato() {
		return this.idPlato;
	}
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
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
			&& this.idPlato.equals(castOther.idPlato)
			&& this.idGenerado.equals(castOther.idGenerado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feConsumo.hashCode();
		hash = hash * prime + this.idPlato.hashCode();
		hash = hash * prime + this.idGenerado.hashCode();
		
		return hash;
	}
}