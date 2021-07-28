package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the menu_generado database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="menu_generado")
@NamedQuery(name="MenuGenerado.findAll", query="SELECT m FROM MenuGenerado m")
public class MenuGenerado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema = "sistema", name="MENU_GENERADO_IDGENERADO_GENERATOR", sequenceName="SEQ_MENU")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_GENERADO_IDGENERADO_GENERATOR")
	@Column(name="id_generado", unique=true, nullable=false)
	private Long idGenerado;

	@Column(name="fe_desde")
	private Timestamp feDesde;

	@Column(name="fe_generado")
	private Timestamp feGenerado;

	@Column(name="fe_hasta")
	private Timestamp feHasta;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_persona")
	private Long idPersona;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="nu_dias")
	private Integer nuDias;

	@Column(name="st_menu", length=1)
	private String stMenu;

	public MenuGenerado() {
	}

	public Long getIdGenerado() {
		return this.idGenerado;
	}

	public void setIdGenerado(Long idGenerado) {
		this.idGenerado = idGenerado;
	}

	public Timestamp getFeDesde() {
		return this.feDesde;
	}

	public void setFeDesde(Timestamp feDesde) {
		this.feDesde = feDesde;
	}

	public Timestamp getFeGenerado() {
		return this.feGenerado;
	}

	public void setFeGenerado(Timestamp feGenerado) {
		this.feGenerado = feGenerado;
	}

	public Timestamp getFeHasta() {
		return this.feHasta;
	}

	public void setFeHasta(Timestamp feHasta) {
		this.feHasta = feHasta;
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

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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

	public String getStMenu() {
		return this.stMenu;
	}

	public void setStMenu(String stMenu) {
		this.stMenu = stMenu;
	}

}