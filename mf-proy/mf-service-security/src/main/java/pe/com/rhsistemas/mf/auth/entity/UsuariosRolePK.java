package pe.com.rhsistemas.mf.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the usuarios_roles database table.
 * 
 */
@Embeddable
public class UsuariosRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="roles_id_rol", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer rolesIdRol;

	@Column(name="usuarios_id_persona", insertable=false, updatable=false, unique=true, nullable=false)
	private Long usuariosIdPersona;

	public UsuariosRolePK() {
	}
	public Integer getRolesIdRol() {
		return this.rolesIdRol;
	}
	public void setRolesIdRol(Integer rolesIdRol) {
		this.rolesIdRol = rolesIdRol;
	}
	public Long getUsuariosIdPersona() {
		return this.usuariosIdPersona;
	}
	public void setUsuariosIdPersona(Long usuariosIdPersona) {
		this.usuariosIdPersona = usuariosIdPersona;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuariosRolePK)) {
			return false;
		}
		UsuariosRolePK castOther = (UsuariosRolePK)other;
		return 
			this.rolesIdRol.equals(castOther.rolesIdRol)
			&& this.usuariosIdPersona.equals(castOther.usuariosIdPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rolesIdRol.hashCode();
		hash = hash * prime + this.usuariosIdPersona.hashCode();
		
		return hash;
	}
}