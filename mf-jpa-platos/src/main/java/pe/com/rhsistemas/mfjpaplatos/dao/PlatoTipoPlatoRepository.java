/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.rhsistemas.mfjpaplatos.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoIngredientePK;
import pe.com.rhsistemas.mfjpaplatos.entity.PlatoTipoPlato;

/**
 * @author Edwin
 *
 */
public interface PlatoTipoPlatoRepository extends JpaRepository<PlatoIngrediente, PlatoIngredientePK> {

	@Query(value = "Select ptp from PlatoTipoPlato ptp where ptp.id.idPlato in (:listaPlatos)")
	List<PlatoTipoPlato> findByPlatoTipoPlatoIn(@Param(value = "listaPlatos") List<Integer> listaPlatos);
}
