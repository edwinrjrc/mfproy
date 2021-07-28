package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_persona", unique=true, nullable=false)
	private Long idPersona;

	@Column(name="fe_caduca")
	private Timestamp feCaduca;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="fe_ulti_actu_pass")
	private Timestamp feUltiActuPass;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(name="in_cuen_cadu", length=1)
	private String inCuenCadu;

	@Column(name="in_estado", length=1)
	private String inEstado;

	@Column(name="tx_login", length=100)
	private String txLogin;

	@Column(name="tx_password", length=500)
	private String txPassword;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="id_persona", nullable=false, insertable=false, updatable=false)
	private Persona persona;

	//bi-directional many-to-one association to UsuariosRole
	@OneToMany(mappedBy="usuario")
	private List<UsuariosRole> usuariosRoles;

	public Usuario() {
	}

	public Long getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Timestamp getFeCaduca() {
		return this.feCaduca;
	}

	public void setFeCaduca(Timestamp feCaduca) {
		this.feCaduca = feCaduca;
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

	public Timestamp getFeUltiActuPass() {
		return this.feUltiActuPass;
	}

	public void setFeUltiActuPass(Timestamp feUltiActuPass) {
		this.feUltiActuPass = feUltiActuPass;
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

	public String getInCuenCadu() {
		return this.inCuenCadu;
	}

	public void setInCuenCadu(String inCuenCadu) {
		this.inCuenCadu = inCuenCadu;
	}

	public String getInEstado() {
		return this.inEstado;
	}

	public void setInEstado(String inEstado) {
		this.inEstado = inEstado;
	}

	public String getTxLogin() {
		return this.txLogin;
	}

	public void setTxLogin(String txLogin) {
		this.txLogin = txLogin;
	}

	public String getTxPassword() {
		return this.txPassword;
	}

	public void setTxPassword(String txPassword) {
		this.txPassword = txPassword;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<UsuariosRole> getUsuariosRoles() {
		return this.usuariosRoles;
	}

	public void setUsuariosRoles(List<UsuariosRole> usuariosRoles) {
		this.usuariosRoles = usuariosRoles;
	}

	public UsuariosRole addUsuariosRole(UsuariosRole usuariosRole) {
		getUsuariosRoles().add(usuariosRole);
		usuariosRole.setUsuario(this);

		return usuariosRole;
	}

	public UsuariosRole removeUsuariosRole(UsuariosRole usuariosRole) {
		getUsuariosRoles().remove(usuariosRole);
		usuariosRole.setUsuario(null);

		return usuariosRole;
	}

}