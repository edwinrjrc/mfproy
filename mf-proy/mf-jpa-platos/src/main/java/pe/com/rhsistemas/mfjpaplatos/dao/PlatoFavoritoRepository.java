/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavorito;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoFavoritoPK;

/**
 * @author Edwin
 *
 */
public interface PlatoFavoritoRepository extends JpaRepository<PlatoFavorito, PlatoFavoritoPK> {

}
