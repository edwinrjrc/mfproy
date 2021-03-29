package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name="persona", schema = "sistema")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_IDPERSONA_GENERATOR", sequenceName="SEQ_PERSONA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_IDPERSONA_GENERATOR")
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_nacimiento")
	private Date feNacimiento;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_tipo_docu")
	private Integer idTipoDocu;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_documento", length=30)
	private String nuDocumento;

	//bi-directional one-to-one association to ConfiguracionFamilia
	@OneToOne(mappedBy="persona")
	private ConfiguracionFamilia configuracionFamilia;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="persona")
	private List<Direccion> direccions;

	//bi-directional many-to-one association to HistoriaInicioSesion
	@OneToMany(mappedBy="persona")
	private List<HistoriaInicioSesion> historiaInicioSesions;

	//bi-directional many-to-one association to MenuGenerado
	@OneToMany(mappedBy="persona")
	private List<MenuGenerado> menuGenerados;

	//bi-directional one-to-one association to PersonaJuridica
	@OneToOne(mappedBy="persona")
	private PersonaJuridica personaJuridica;

	//bi-directional one-to-one association to PersonaNatural
	@OneToOne(mappedBy="persona")
	private PersonaNatural personaNatural;

	//bi-directional many-to-one association to Plato
	@OneToMany(mappedBy="persona")
	private List<Plato> platos;

	//bi-directional many-to-one association to PlatoFavorito
	@OneToMany(mappedBy="persona")
	private List<PlatoFavorito> platoFavoritos;

	//bi-directional one-to-one association to Usuario
	@OneToOne(mappedBy="persona")
	private Usuario usuario;

	public Persona() {
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Timestamp getFeModificacion() {
		return this.feModificacion;
	}

	public void setFeModificacion(Timestamp feModificacion) {
		this.feModificacion = feModificacion;
	}

	public Date getFeNacimiento() {
		return this.feNacimiento;
	}

	public void setFeNacimiento(Date feNacimiento) {
		this.feNacimiento = feNacimiento;
	}

	public Timestamp getFeRegistro() {
		return this.feRegistro;
	}

	public void setFeRegistro(Timestamp feRegistro) {
		this.feRegistro = feRegistro;
	}

	public Integer getIdTipoDocu() {
		return this.idTipoDocu;
	}

	public void setIdTipoDocu(Integer idTipoDocu) {
		this.idTipoDocu = idTipoDocu;
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

	public String getNuDocumento() {
		return this.nuDocumento;
	}

	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}

	public ConfiguracionFamilia getConfiguracionFamilia() {
		return this.configuracionFamilia;
	}

	public void setConfiguracionFamilia(ConfiguracionFamilia configuracionFamilia) {
		this.configuracionFamilia = configuracionFamilia;
	}

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setPersona(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setPersona(null);

		return direccion;
	}

	public List<HistoriaInicioSesion> getHistoriaInicioSesions() {
		return this.historiaInicioSesions;
	}

	public void setHistoriaInicioSesions(List<HistoriaInicioSesion> historiaInicioSesions) {
		this.historiaInicioSesions = historiaInicioSesions;
	}

	public HistoriaInicioSesion addHistoriaInicioSesion(HistoriaInicioSesion historiaInicioSesion) {
		getHistoriaInicioSesions().add(historiaInicioSesion);
		historiaInicioSesion.setPersona(this);

		return historiaInicioSesion;
	}

	public HistoriaInicioSesion removeHistoriaInicioSesion(HistoriaInicioSesion historiaInicioSesion) {
		getHistoriaInicioSesions().remove(historiaInicioSesion);
		historiaInicioSesion.setPersona(null);

		return historiaInicioSesion;
	}

	public List<MenuGenerado> getMenuGenerados() {
		return this.menuGenerados;
	}

	public void setMenuGenerados(List<MenuGenerado> menuGenerados) {
		this.menuGenerados = menuGenerados;
	}

	public MenuGenerado addMenuGenerado(MenuGenerado menuGenerado) {
		getMenuGenerados().add(menuGenerado);
		menuGenerado.setPersona(this);

		return menuGenerado;
	}

	public MenuGenerado removeMenuGenerado(MenuGenerado menuGenerado) {
		getMenuGenerados().remove(menuGenerado);
		menuGenerado.setPersona(null);

		return menuGenerado;
	}

	public PersonaJuridica getPersonaJuridica() {
		return this.personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public PersonaNatural getPersonaNatural() {
		return this.personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public List<Plato> getPlatos() {
		return this.platos;
	}

	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}

	public Plato addPlato(Plato plato) {
		getPlatos().add(plato);
		plato.setPersona(this);

		return plato;
	}

	public Plato removePlato(Plato plato) {
		getPlatos().remove(plato);
		plato.setPersona(null);

		return plato;
	}

	public List<PlatoFavorito> getPlatoFavoritos() {
		return this.platoFavoritos;
	}

	public void setPlatoFavoritos(List<PlatoFavorito> platoFavoritos) {
		this.platoFavoritos = platoFavoritos;
	}

	public PlatoFavorito addPlatoFavorito(PlatoFavorito platoFavorito) {
		getPlatoFavoritos().add(platoFavorito);
		platoFavorito.setPersona(this);

		return platoFavorito;
	}

	public PlatoFavorito removePlatoFavorito(PlatoFavorito platoFavorito) {
		getPlatoFavoritos().remove(platoFavorito);
		platoFavorito.setPersona(null);

		return platoFavorito;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}