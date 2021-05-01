package pe.com.rhsistemas.mfjpaingrediente.entity;

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

	@Column(name="roles_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer rolesId;

	@Column(name="usuarios_id_persona", insertable=false, updatable=false, unique=true, nullable=false)
	private Long usuariosIdPersona;

	public UsuariosRolePK() {
	}
	public Integer getRolesId() {
		return this.rolesId;
	}
	public void setRolesId(Integer rolesId) {
		this.rolesId = rolesId;
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
			this.rolesId.equals(castOther.rolesId)
			&& this.usuariosIdPersona.equals(castOther.usuariosIdPersona);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rolesId.hashCode();
		hash = hash * prime + this.usuariosIdPersona.hashCode();
		
		return hash;
	}
}