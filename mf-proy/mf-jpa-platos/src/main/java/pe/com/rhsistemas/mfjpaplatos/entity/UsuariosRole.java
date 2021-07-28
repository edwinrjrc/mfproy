package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the usuarios_roles database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="usuarios_roles")
@NamedQuery(name="UsuariosRole.findAll", query="SELECT u FROM UsuariosRole u")
public class UsuariosRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuariosRolePK id;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roles_id_rol", nullable=false, insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuarios_id_persona", nullable=false, insertable=false, updatable=false)
	private Usuario usuario;

	public UsuariosRole() {
	}

	public UsuariosRolePK getId() {
		return this.id;
	}

	public void setId(UsuariosRolePK id) {
		this.id = id;
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}