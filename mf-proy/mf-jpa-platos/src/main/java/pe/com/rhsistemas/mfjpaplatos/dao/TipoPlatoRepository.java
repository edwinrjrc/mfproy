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
	
	//distinct tp2.id_tipo_plat, tp2.de_tipo_plat
	@Query(value = "Select tp"
			+ "       from TipoPlato tp "
			+ "      where tp.inFondo = ?1 "
			+ "        and tp.idTipoPlat in (select ptp.id.idTipoPlat from PlatoTipoPlato ptp) ")
	List<TipoPlato> findByTiposxPlatoInFondo(String inFondo);
}
