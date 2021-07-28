package pe.com.rhsistemas.mfjpaingrediente.entity;

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
 * The persistent class for the plato database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="plato")
@NamedQuery(name="Plato.findAll", query="SELECT p FROM Plato p")
public class Plato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema = "sistema", name="PLATO_IDPLATO_GENERATOR", sequenceName="SEQ_PLATO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PLATO_IDPLATO_GENERATOR")
	@Column(name="id_plato", unique=true, nullable=false)
	private Integer idPlato;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_esta_plat")
	private Integer idEstaPlat;

	@Column(name="id_persona")
	private Long idPersona;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="in_acompaniamiento", length=1)
	private String inAcompaniamiento;

	@Column(name="no_plato", length=100)
	private String noPlato;

	//bi-directional many-to-one association to TipoCocina
	@ManyToOne
	@JoinColumn(name="id_tipo_coci", nullable=false)
	private TipoCocina tipoCocina;

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

	public TipoCocina getTipoCocina() {
		return this.tipoCocina;
	}

	public void setTipoCocina(TipoCocina tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

}