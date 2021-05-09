package pe.com.rhsistemas.mf.auth.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ingrediente database table.
 * 
 */
@Entity
@Table(name="ingrediente", schema = "sistema")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INGREDIENTE_IDINGREDIENTE_GENERATOR", sequenceName="SEQ_INGREDIENTE", schema = "sistema")
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
	private float nuCalorias;

	@Column(name="nu_grasas")
	private float nuGrasas;

	@Column(name="nu_proteina")
	private float nuProteina;

	//bi-directional many-to-one association to TipoIngrediente
	@ManyToOne
	@JoinColumn(name="id_tipo_ingr", nullable=false)
	private TipoIngrediente tipoIngrediente;

	//bi-directional many-to-one association to PlatoIngrediente
	@OneToMany(mappedBy="ingrediente")
	private List<PlatoIngrediente> platoIngredientes;

	//bi-directional many-to-one association to Receta
	@OneToMany(mappedBy="ingrediente")
	private List<Receta> recetas;

	//bi-directional many-to-one association to ValorNutricional
	@OneToMany(mappedBy="ingrediente")
	private List<ValorNutricional> valorNutricionals;

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

	public float getNuCalorias() {
		return this.nuCalorias;
	}

	public void setNuCalorias(float nuCalorias) {
		this.nuCalorias = nuCalorias;
	}

	public float getNuGrasas() {
		return this.nuGrasas;
	}

	public void setNuGrasas(float nuGrasas) {
		this.nuGrasas = nuGrasas;
	}

	public float getNuProteina() {
		return this.nuProteina;
	}

	public void setNuProteina(float nuProteina) {
		this.nuProteina = nuProteina;
	}

	public TipoIngrediente getTipoIngrediente() {
		return this.tipoIngrediente;
	}

	public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}

	public List<PlatoIngrediente> getPlatoIngredientes() {
		return this.platoIngredientes;
	}

	public void setPlatoIngredientes(List<PlatoIngrediente> platoIngredientes) {
		this.platoIngredientes = platoIngredientes;
	}

	public PlatoIngrediente addPlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().add(platoIngrediente);
		platoIngrediente.setIngrediente(this);

		return platoIngrediente;
	}

	public PlatoIngrediente removePlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().remove(platoIngrediente);
		platoIngrediente.setIngrediente(null);

		return platoIngrediente;
	}

	public List<Receta> getRecetas() {
		return this.recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public Receta addReceta(Receta receta) {
		getRecetas().add(receta);
		receta.setIngrediente(this);

		return receta;
	}

	public Receta removeReceta(Receta receta) {
		getRecetas().remove(receta);
		receta.setIngrediente(null);

		return receta;
	}

	public List<ValorNutricional> getValorNutricionals() {
		return this.valorNutricionals;
	}

	public void setValorNutricionals(List<ValorNutricional> valorNutricionals) {
		this.valorNutricionals = valorNutricionals;
	}

	public ValorNutricional addValorNutricional(ValorNutricional valorNutricional) {
		getValorNutricionals().add(valorNutricional);
		valorNutricional.setIngrediente(this);

		return valorNutricional;
	}

	public ValorNutricional removeValorNutricional(ValorNutricional valorNutricional) {
		getValorNutricionals().remove(valorNutricional);
		valorNutricional.setIngrediente(null);

		return valorNutricional;
	}

}