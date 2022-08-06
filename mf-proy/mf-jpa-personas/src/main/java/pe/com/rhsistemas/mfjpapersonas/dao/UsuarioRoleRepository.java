/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpapersonas.entity.UsuariosRole;
import pe.com.rhsistemas.mfjpapersonas.entity.UsuariosRolePK;

/**
 * @author Edwin
 *
 */
public interface UsuarioRoleRepository extends JpaRepository<UsuariosRole, UsuariosRolePK> {

}
