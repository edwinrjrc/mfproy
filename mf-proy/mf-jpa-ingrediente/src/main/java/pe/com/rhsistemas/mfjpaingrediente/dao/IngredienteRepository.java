/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaingrediente.entity.Ingrediente;


/**
 * @author Edwin
 *
 */
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{
	
	public List<Ingrediente> findByOrderByDeIngredienteAsc();
	
}
