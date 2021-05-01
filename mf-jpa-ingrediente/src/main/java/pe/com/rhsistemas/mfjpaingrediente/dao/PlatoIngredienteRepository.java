/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngredientePK;


/**
 * @author Edwin
 *
 */
public interface PlatoIngredienteRepository extends JpaRepository<PlatoIngrediente, PlatoIngredientePK> {

	@Query(value = "Select pi from PlatoIngrediente pi where pi.id.idPlato = ?1")
	public List<PlatoIngrediente> findAllByPlato(Integer idPlato);
}
