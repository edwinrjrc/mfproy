/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpapersonas.entity.Usuario;

/**
 * @author Edwin
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByTxLogin(String txLogin);
}
