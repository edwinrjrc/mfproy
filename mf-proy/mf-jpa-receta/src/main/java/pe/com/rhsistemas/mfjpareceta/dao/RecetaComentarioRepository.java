/**
 * 
 */
package pe.com.rhsistemas.mfjpareceta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpareceta.entity.RecetaComentario;

/**
 * @author Edwin
 *
 */
public interface RecetaComentarioRepository extends JpaRepository<RecetaComentario, Long> {

	List<RecetaComentario> findByIdPlato(Integer idPlato);
}
