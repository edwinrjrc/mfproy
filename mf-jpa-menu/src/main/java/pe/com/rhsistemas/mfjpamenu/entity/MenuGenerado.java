package pe.com.rhsistemas.mfjpamenu.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the menu_generado database table.
 * 
 */
@Entity
@Table(name="menu_generado", schema = "sistema")
@NamedQuery(name="MenuGenerado.findAll", query="SELECT m FROM MenuGenerado m")
@NamedQuery(name="MenuGenerado.findByUltimoMenu", query="SELECT g FROM MenuGenerado g WHERE g.idGenerado = (SELECT MAX(m.idGenerado) FROM MenuGenerado m WHERE m.persona = ?1)")
public class MenuGenerado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MENU_GENERADO_IDGENERADO_GENERATOR", sequenceName="SEQ_MENU", schema = "sistema", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_GENERADO_IDGENERADO_GENERATOR")
	@Column(name="id_generado", unique=true, nullable=false)
	private Long idGenerado;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_generado")
	private Date feGenerado;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_dias")
	private Integer nuDias;

	//bi-directional many-to-one association to MenuDetalle
	@OneToMany(mappedBy="menuGenerado", cascade = {CascadeType.PERSIST})
	private List<MenuDetalle> menuDetalles;

	//bi-directional many-to-one association to Persona
	@ManyToOne()
	@JoinColumn(name="id_persona", nullable=false)
	private Persona persona;

	public MenuGenerado() {
	}

	public Long getIdGenerado() {
		return this.idGenerado;
	}

	public void setIdGenerado(Long idGenerado) {
		this.idGenerado = idGenerado;
	}

	public Date getFeGenerado() {
		return this.feGenerado;
	}

	public void setFeGenerado(Date feGenerado) {
		this.feGenerado = feGenerado;
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

	public Integer getNuDias() {
		return this.nuDias;
	}

	public void setNuDias(Integer nuDias) {
		this.nuDias = nuDias;
	}

	public List<MenuDetalle> getMenuDetalles() {
		return this.menuDetalles;
	}

	public void setMenuDetalles(List<MenuDetalle> menuDetalles) {
		this.menuDetalles = menuDetalles;
	}

	public MenuDetalle addMenuDetalle(MenuDetalle menuDetalle) {
		getMenuDetalles().add(menuDetalle);
		menuDetalle.setMenuGenerado(this);

		return menuDetalle;
	}

	public MenuDetalle removeMenuDetalle(MenuDetalle menuDetalle) {
		getMenuDetalles().remove(menuDetalle);
		menuDetalle.setMenuGenerado(null);

		return menuDetalle;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}