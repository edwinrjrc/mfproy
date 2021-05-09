package pe.com.rhsistemas.mf.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the plato_ingrediente database table.
 * 
 */
@Entity
@Table(name="plato_ingrediente", schema = "sistema")
@NamedQuery(name="PlatoIngrediente.findAll", query="SELECT p FROM PlatoIngrediente p")
public class PlatoIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoIngredientePK id;

	@Column(name="id_unid_medi")
	private Integer idUnidMedi;

	@Column(name="nu_cantidad")
	private float nuCantidad;

	@Column(name="nu_orden")
	private Integer nuOrden;

	@Column(name="ti_ingrediente", length=254)
	private String tiIngrediente;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="id_ingrediente", nullable=false, insertable=false, updatable=false)
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="id_plato", nullable=false, insertable=false, updatable=false)
	private Plato plato;

	public PlatoIngrediente() {
	}

	public PlatoIngredientePK getId() {
		return this.id;
	}

	public void setId(PlatoIngredientePK id) {
		this.id = id;
	}

	public Integer getIdUnidMedi() {
		return this.idUnidMedi;
	}

	public void setIdUnidMedi(Integer idUnidMedi) {
		this.idUnidMedi = idUnidMedi;
	}

	public float getNuCantidad() {
		return this.nuCantidad;
	}

	public void setNuCantidad(float nuCantidad) {
		this.nuCantidad = nuCantidad;
	}

	public Integer getNuOrden() {
		return this.nuOrden;
	}

	public void setNuOrden(Integer nuOrden) {
		this.nuOrden = nuOrden;
	}

	public String getTiIngrediente() {
		return this.tiIngrediente;
	}

	public void setTiIngrediente(String tiIngrediente) {
		this.tiIngrediente = tiIngrediente;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Plato getPlato() {
		return this.plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

}