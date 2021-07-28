package pe.com.rhsistemas.mfjpaingrediente.entity;

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
 * The persistent class for the valor_nutricional database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="valor_nutricional")
@NamedQuery(name="ValorNutricional.findAll", query="SELECT v FROM ValorNutricional v")
public class ValorNutricional implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ValorNutricionalPK id;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_aporte")
	private float nuAporte;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="id_ingrediente", nullable=false, insertable=false, updatable=false)
	private Ingrediente ingrediente;

	public ValorNutricional() {
	}

	public ValorNutricionalPK getId() {
		return this.id;
	}

	public void setId(ValorNutricionalPK id) {
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

	public float getNuAporte() {
		return this.nuAporte;
	}

	public void setNuAporte(float nuAporte) {
		this.nuAporte = nuAporte;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

}