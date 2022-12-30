package pe.com.rhsistemas.mfjpaconfiguracion.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ingrediente database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="ingrediente")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1,schema = "sistema", name="INGREDIENTE_IDINGREDIENTE_GENERATOR", sequenceName="SEQ_INGREDIENTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INGREDIENTE_IDINGREDIENTE_GENERATOR")
	@Column(name="id_ingrediente", unique=true, nullable=false)
	private Integer idIngrediente;

	@Column(name="de_ingrediente", length=100)
	private String deIngrediente;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_calorias")
	private Float nuCalorias;

	@Column(name="nu_grasas")
	private Float nuGrasas;

	@Column(name="nu_proteina")
	private Float nuProteina;

	//bi-directional many-to-one association to TipoIngrediente
	@ManyToOne
	@JoinColumn(name="id_tipo_ingr", nullable=false)
	private TipoIngrediente tipoIngrediente;

	public Ingrediente() {
	}

	public Integer getIdIngrediente() {
		return this.idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getDeIngrediente() {
		return this.deIngrediente;
	}

	public void setDeIngrediente(String deIngrediente) {
		this.deIngrediente = deIngrediente;
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

	public Float getNuCalorias() {
		return this.nuCalorias;
	}

	public void setNuCalorias(Float nuCalorias) {
		this.nuCalorias = nuCalorias;
	}

	public Float getNuGrasas() {
		return this.nuGrasas;
	}

	public void setNuGrasas(Float nuGrasas) {
		this.nuGrasas = nuGrasas;
	}

	public Float getNuProteina() {
		return this.nuProteina;
	}

	public void setNuProteina(Float nuProteina) {
		this.nuProteina = nuProteina;
	}

	public TipoIngrediente getTipoIngrediente() {
		return this.tipoIngrediente;
	}

	public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}

}