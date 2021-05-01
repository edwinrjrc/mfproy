package pe.com.rhsistemas.mfjpaingrediente.entity;

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
 * The persistent class for the plato database table.
 * 
 */
@Entity
@Table(name="plato", schema = "sistema")
@NamedQuery(name="Plato.findAll", query="SELECT p FROM Plato p")
public class Plato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PLATO_IDPLATO_GENERATOR", sequenceName="SEQ_PLATO", schema = "sistema")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLATO_IDPLATO_GENERATOR")
	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_esta_plat")
	private Integer idEstaPlat;

	@Column(name="id_tipo_plato")
	private Integer idTipoPlato;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="in_acompaniamiento", length=254)
	private String inAcompaniamiento;

	@Column(name="no_plato", length=100)
	private String noPlato;

	//bi-directional many-to-one association to MenuDetalle
	@OneToMany(mappedBy="plato")
	private List<MenuDetalle> menuDetalles;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona", nullable=false)
	private Persona persona;

	//bi-directional many-to-one association to TipoCocina
	@ManyToOne
	@JoinColumn(name="id_tipo_coci", nullable=false)
	private TipoCocina tipoCocina;

	//bi-directional many-to-one association to PlatoFavorito
	@OneToMany(mappedBy="plato")
	private List<PlatoFavorito> platoFavoritos;

	//bi-directional many-to-one association to PlatoIngrediente
	@OneToMany(mappedBy="plato")
	private List<PlatoIngrediente> platoIngredientes;

	//bi-directional many-to-one association to Receta
	@OneToMany(mappedBy="plato")
	private List<Receta> recetas;

	public Plato() {
	}

	public Integer getIdPlato() {
		return this.idPlato;
	}

	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
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

	public Integer getIdEstaPlat() {
		return this.idEstaPlat;
	}

	public void setIdEstaPlat(Integer idEstaPlat) {
		this.idEstaPlat = idEstaPlat;
	}

	public Integer getIdTipoPlato() {
		return this.idTipoPlato;
	}

	public void setIdTipoPlato(Integer idTipoPlato) {
		this.idTipoPlato = idTipoPlato;
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

	public String getInAcompaniamiento() {
		return this.inAcompaniamiento;
	}

	public void setInAcompaniamiento(String inAcompaniamiento) {
		this.inAcompaniamiento = inAcompaniamiento;
	}

	public String getNoPlato() {
		return this.noPlato;
	}

	public void setNoPlato(String noPlato) {
		this.noPlato = noPlato;
	}

	public List<MenuDetalle> getMenuDetalles() {
		return this.menuDetalles;
	}

	public void setMenuDetalles(List<MenuDetalle> menuDetalles) {
		this.menuDetalles = menuDetalles;
	}

	public MenuDetalle addMenuDetalle(MenuDetalle menuDetalle) {
		getMenuDetalles().add(menuDetalle);
		menuDetalle.setPlato(this);

		return menuDetalle;
	}

	public MenuDetalle removeMenuDetalle(MenuDetalle menuDetalle) {
		getMenuDetalles().remove(menuDetalle);
		menuDetalle.setPlato(null);

		return menuDetalle;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoCocina getTipoCocina() {
		return this.tipoCocina;
	}

	public void setTipoCocina(TipoCocina tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public List<PlatoFavorito> getPlatoFavoritos() {
		return this.platoFavoritos;
	}

	public void setPlatoFavoritos(List<PlatoFavorito> platoFavoritos) {
		this.platoFavoritos = platoFavoritos;
	}

	public PlatoFavorito addPlatoFavorito(PlatoFavorito platoFavorito) {
		getPlatoFavoritos().add(platoFavorito);
		platoFavorito.setPlato(this);

		return platoFavorito;
	}

	public PlatoFavorito removePlatoFavorito(PlatoFavorito platoFavorito) {
		getPlatoFavoritos().remove(platoFavorito);
		platoFavorito.setPlato(null);

		return platoFavorito;
	}

	public List<PlatoIngrediente> getPlatoIngredientes() {
		return this.platoIngredientes;
	}

	public void setPlatoIngredientes(List<PlatoIngrediente> platoIngredientes) {
		this.platoIngredientes = platoIngredientes;
	}

	public PlatoIngrediente addPlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().add(platoIngrediente);
		platoIngrediente.setPlato(this);

		return platoIngrediente;
	}

	public PlatoIngrediente removePlatoIngrediente(PlatoIngrediente platoIngrediente) {
		getPlatoIngredientes().remove(platoIngrediente);
		platoIngrediente.setPlato(null);

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
		receta.setPlato(this);

		return receta;
	}

	public Receta removeReceta(Receta receta) {
		getRecetas().remove(receta);
		receta.setPlato(null);

		return receta;
	}

}