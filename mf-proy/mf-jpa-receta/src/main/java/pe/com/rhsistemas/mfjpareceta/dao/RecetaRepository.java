/**
 * 
 */
package pe.com.rhsistemas.mfjpareceta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpareceta.entity.Receta;
import pe.com.rhsistemas.mfjpareceta.entity.RecetaPK;

/**
 * @author Edwin
 *
 */
public interface RecetaRepository extends JpaRepository<Receta, RecetaPK> {

	@Query(value = "Select r from Receta r where r.id.idPlato = ?1 order by r.id.idPaso asc")
	List<Receta> findByIdPlato(Integer idPlato);
	
}
