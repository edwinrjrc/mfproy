package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "plato_ingrediente")
@NamedQuery(name = "PlatoIngrediente.findAll", query = "SELECT p FROM PlatoIngrediente p")
public class PlatoIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlatoIngredientePK id;

	@Column(name = "fe_modificacion", nullable = false)
	private Timestamp feModificacion;

	@Column(name = "fe_registro", nullable = false)
	private Timestamp feRegistro;

	@Column(name = "id_unid_medi", nullable = false)
	private Integer idUnidMedi;

	@Column(name = "id_usua_crea", nullable = false)
	private Integer idUsuaCrea;

	@Column(name = "id_usua_modi", nullable = false)
	private Integer idUsuaModi;

	@Column(name = "nu_cantidad")
	private float nuCantidad;

	@Column(name = "nu_orden")
	private Integer nuOrden;

	@Column(name = "ti_ingrediente", length = 1)
	private String tiIngrediente;

	// bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name = "id_ingrediente", nullable = false, insertable = false, updatable = false)
	private Ingrediente ingrediente;

	// bi-directional many-to-one association to UnidadMedida
	@ManyToOne
	@JoinColumn(name = "id_unid_medi", nullable = false, insertable = false, updatable = false)
	private UnidadMedida unidadMedida;

	public PlatoIngrediente() {
	}

	public PlatoIngredientePK getId() {
		return this.id;
	}

	public void setId(PlatoIngredientePK id) {
		this.id = id;
	}

	public Timestamp getFeModificacion() {
		return this.feModificacion;
	}

	public void setFeModificacion(Timestamp feModificacion) {
		this.feModificacion = feModificacion;
	}

	public Timestamp getFeRegistro() {
		return this.feRegistro;
	}

	public void setFeRegistro(Timestamp feRegistro) {
		this.feRegistro = feRegistro;
	}

	public Integer getIdUnidMedi() {
		return this.idUnidMedi;
	}

	public void setIdUnidMedi(Integer idUnidMedi) {
		this.idUnidMedi = idUnidMedi;
	}

	public Integer getIdUsuaCrea() {
		return this.idUsuaCrea;
	}

	public void setIdUsuaCrea(Integer idUsuaCrea) {
		this.idUsuaCrea = idUsuaCrea;
	}

	public Integer getIdUsuaModi() {
		return this.idUsuaModi;
	}

	public void setIdUsuaModi(Integer idUsuaModi) {
		this.idUsuaModi = idUsuaModi;
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
	public UnidadMedida getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}