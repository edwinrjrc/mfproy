package pe.com.rhsistemas.mfjpapersonas.entity;

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
 * The persistent class for the direccion database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="direccion")
@NamedQuery(name="Direccion.findAll", query="SELECT d FROM Direccion d")
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1,schema = "sistema", name="DIRECCION_IDDIRECCION_GENERATOR", sequenceName="SEQ_DIRECCION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIRECCION_IDDIRECCION_GENERATOR")
	@Column(name="id_direccion", unique=true, nullable=false)
	private Long idDireccion;

	@Column(name="de_observacion", length=300)
	private String deObservacion;

	@Column(name="de_referencia", length=300)
	private String deReferencia;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_persona")
	private Long idPersona;

	@Column(name="id_tipo_via")
	private Integer idTipoVia;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="no_via", length=100)
	private String noVia;

	@Column(name="nu_direccion", length=10)
	private String nuDireccion;

	@Column(name="nu_interior", length=10)
	private String nuInterior;

	@Column(name="nu_lote", length=10)
	private String nuLote;

	@Column(name="nu_manzana", length=10)
	private String nuManzana;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="id_ubigeo", nullable=false)
	private Ubigeo ubigeo;

	public Direccion() {
	}

	public Long getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getDeObservacion() {
		return this.deObservacion;
	}

	public void setDeObservacion(String deObservacion) {
		this.deObservacion = deObservacion;
	}

	public String getDeReferencia() {
		return this.deReferencia;
	}

	public void setDeReferencia(String deReferencia) {
		this.deReferencia = deReferencia;
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

	public Integer getIdTipoVia() {
		return this.idTipoVia;
	}

	public void setIdTipoVia(Integer idTipoVia) {
		this.idTipoVia = idTipoVia;
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

	public String getNoVia() {
		return this.noVia;
	}

	public void setNoVia(String noVia) {
		this.noVia = noVia;
	}

	public String getNuDireccion() {
		return this.nuDireccion;
	}

	public void setNuDireccion(String nuDireccion) {
		this.nuDireccion = nuDireccion;
	}

	public String getNuInterior() {
		return this.nuInterior;
	}

	public void setNuInterior(String nuInterior) {
		this.nuInterior = nuInterior;
	}

	public String getNuLote() {
		return this.nuLote;
	}

	public void setNuLote(String nuLote) {
		this.nuLote = nuLote;
	}

	public String getNuManzana() {
		return this.nuManzana;
	}

	public void setNuManzana(String nuManzana) {
		this.nuManzana = nuManzana;
	}

	public Ubigeo getUbigeo() {
		return this.ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}

}