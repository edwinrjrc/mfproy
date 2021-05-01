/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaingrediente.entity.Ingrediente;


/**
 * @author Edwin
 *
 */
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{
	
}
