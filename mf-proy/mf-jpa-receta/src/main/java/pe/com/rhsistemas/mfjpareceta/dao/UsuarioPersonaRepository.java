/**
 * 
 */
package pe.com.rhsistemas.mfjpareceta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpareceta.entity.Usuario;

/**
 * @author Edwin
 *
 */
public interface UsuarioPersonaRepository extends JpaRepository<Usuario, Long> {

}
