/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpaplatos.entity.TipoPlato;

/**
 * @author Edwin
 *
 */
public interface TipoPlatoRepository extends JpaRepository<TipoPlato, Integer> {
	
	List<TipoPlato> findByInEntrada(String inEntrada);
	
	List<TipoPlato> findByInFondo(String inFondo);
	
	@Query(value = "Select tp from TipoPlato tp inner join PlatoTipoPlato ptp on ptp.id.idTipoPlat = tp.idTipoPlat where tp.inFondo = ?1")
	List<TipoPlato> findByTiposxPlatoInFondo(String inFondo);
}
