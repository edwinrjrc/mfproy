package pe.com.rhsistemas.mfjpapersonas.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the persona_juridica database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="persona_juridica")
@NamedQuery(name="PersonaJuridica.findAll", query="SELECT p FROM PersonaJuridica p")
public class PersonaJuridica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="tx_nomb_come", length=100)
	private String txNombCome;

	@Column(name="tx_razo_soci", length=100)
	private String txRazoSoci;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_persona", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public PersonaJuridica() {
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

	public String getTxNombCome() {
		return this.txNombCome;
	}

	public void setTxNombCome(String txNombCome) {
		this.txNombCome = txNombCome;
	}

	public String getTxRazoSoci() {
		return this.txRazoSoci;
	}

	public void setTxRazoSoci(String txRazoSoci) {
		this.txRazoSoci = txRazoSoci;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}