/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavorito;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavoritoPK;

/**
 * @author Edwin
 *
 */
public interface PlatoFavoritoRepository extends JpaRepository<PlatoFavorito, PlatoFavoritoPK> {
	
	@Query(value = "Select f from PlatoFavorito f where f.id.idPersona = ?1")
	List<PlatoFavorito> findByIdPersona(Long idPersona);
}
