package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the persona_natural database table.
 * 
 */
@Entity
@Table(name="persona_natural")
@NamedQuery(name="PersonaNatural.findAll", query="SELECT p FROM PersonaNatural p")
public class PersonaNatural implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="de_correo", length=50)
	private String deCorreo;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="no_persona", length=100)
	private String noPersona;

	@Column(name="nu_tele_1", length=15)
	private String nuTele1;

	@Column(name="nu_tele_2", length=15)
	private String nuTele2;

	@Column(name="tx_prim_apel", length=100)
	private String txPrimApel;

	@Column(name="tx_segu_apel", length=100)
	private String txSeguApel;

	//bi-directional one-to-one association to Nutricionista
	@OneToOne(mappedBy="personaNatural")
	private Nutricionista nutricionista;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_persona", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	public PersonaNatural() {
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getDeCorreo() {
		return this.deCorreo;
	}

	public void setDeCorreo(String deCorreo) {
		this.deCorreo = deCorreo;
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

	public String getNoPersona() {
		return this.noPersona;
	}

	public void setNoPersona(String noPersona) {
		this.noPersona = noPersona;
	}

	public String getNuTele1() {
		return this.nuTele1;
	}

	public void setNuTele1(String nuTele1) {
		this.nuTele1 = nuTele1;
	}

	public String getNuTele2() {
		return this.nuTele2;
	}

	public void setNuTele2(String nuTele2) {
		this.nuTele2 = nuTele2;
	}

	public String getTxPrimApel() {
		return this.txPrimApel;
	}

	public void setTxPrimApel(String txPrimApel) {
		this.txPrimApel = txPrimApel;
	}

	public String getTxSeguApel() {
		return this.txSeguApel;
	}

	public void setTxSeguApel(String txSeguApel) {
		this.txSeguApel = txSeguApel;
	}

	public Nutricionista getNutricionista() {
		return this.nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}