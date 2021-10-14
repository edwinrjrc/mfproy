package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tipo_ingrediente database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="tipo_ingrediente")
@NamedQuery(name="TipoIngrediente.findAll", query="SELECT t FROM TipoIngrediente t")
public class TipoIngrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_ingr", unique=true, nullable=false)
	private Integer idTipoIngr;

	@Column(name="fe_modificacion")
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_modi")
	private Integer idUsuaModi;

	@Column(name="id_usua_regi")
	private Integer idUsuaRegi;

	@Column(name="no_tipo_ingr", length=100)
	private String noTipoIngr;

	//bi-directional many-to-one association to Ingrediente
	@OneToMany(mappedBy="tipoIngrediente")
	private List<Ingrediente> ingredientes;

	public TipoIngrediente() {
	}

	public Integer getIdTipoIngr() {
		return this.idTipoIngr;
	}

	public void setIdTipoIngr(Integer idTipoIngr) {
		this.idTipoIngr = idTipoIngr;
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

	public Integer getIdUsuaModi() {
		return this.idUsuaModi;
	}

	public void setIdUsuaModi(Integer idUsuaModi) {
		this.idUsuaModi = idUsuaModi;
	}

	public Integer getIdUsuaRegi() {
		return this.idUsuaRegi;
	}

	public void setIdUsuaRegi(Integer idUsuaRegi) {
		this.idUsuaRegi = idUsuaRegi;
	}

	public String getNoTipoIngr() {
		return this.noTipoIngr;
	}

	public void setNoTipoIngr(String noTipoIngr) {
		this.noTipoIngr = noTipoIngr;
	}

	public List<Ingrediente> getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Ingrediente addIngrediente(Ingrediente ingrediente) {
		getIngredientes().add(ingrediente);
		ingrediente.setTipoIngrediente(this);

		return ingrediente;
	}

	public Ingrediente removeIngrediente(Ingrediente ingrediente) {
		getIngredientes().remove(ingrediente);
		ingrediente.setTipoIngrediente(null);

		return ingrediente;
	}

}