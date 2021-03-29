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
 * The persistent class for the receta database table.
 * 
 */
@Entity
@Table(name="receta", schema = "sistema")
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecetaPK id;

	@Column(name="de_paso_rece", length=500)
	private String dePasoRece;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="in_paso_cocc")
	private Integer inPasoCocc;

	@Column(name="nu_minu_comp")
	private Integer nuMinuComp;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne()
	@JoinColumn(name="id_ingrediente", nullable=false)
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Plato
	@ManyToOne
	@JoinColumn(name="id_plato", nullable=false, insertable=false, updatable=false)
	private Plato plato;

	public Receta() {
	}

	public RecetaPK getId() {
		return this.id;
	}

	public void setId(RecetaPK id) {
		this.id = id;
	}

	public String getDePasoRece() {
		return this.dePasoRece;
	}

	public void setDePasoRece(String dePasoRece) {
		this.dePasoRece = dePasoRece;
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

	public Integer getInPasoCocc() {
		return this.inPasoCocc;
	}

	public void setInPasoCocc(Integer inPasoCocc) {
		this.inPasoCocc = inPasoCocc;
	}

	public Integer getNuMinuComp() {
		return this.nuMinuComp;
	}

	public void setNuMinuComp(Integer nuMinuComp) {
		this.nuMinuComp = nuMinuComp;
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