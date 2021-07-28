package pe.com.rhsistemas.mfjpaplatos.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(schema = "sistema", name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_rol", unique=true, nullable=false)
	private Integer idRol;

	@Column(name="fe_modificacion", nullable=false)
	private Timestamp feModificacion;

	@Column(name="fe_registro", nullable=false)
	private Timestamp feRegistro;

	@Column(name="id_usua_crea", nullable=false)
	private Integer idUsuaCrea;

	@Column(name="id_usua_modi", nullable=false)
	private Integer idUsuaModi;

	@Column(length=100)
	private String nombre;

	//bi-directional many-to-one association to UsuariosRole
	@OneToMany(mappedBy="role")
	private List<UsuariosRole> usuariosRoles;

	public Role() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UsuariosRole> getUsuariosRoles() {
		return this.usuariosRoles;
	}

	public void setUsuariosRoles(List<UsuariosRole> usuariosRoles) {
		this.usuariosRoles = usuariosRoles;
	}

	public UsuariosRole addUsuariosRole(UsuariosRole usuariosRole) {
		getUsuariosRoles().add(usuariosRole);
		usuariosRole.setRole(this);

		return usuariosRole;
	}

	public UsuariosRole removeUsuariosRole(UsuariosRole usuariosRole) {
		getUsuariosRoles().remove(usuariosRole);
		usuariosRole.setRole(null);

		return usuariosRole;
	}

}