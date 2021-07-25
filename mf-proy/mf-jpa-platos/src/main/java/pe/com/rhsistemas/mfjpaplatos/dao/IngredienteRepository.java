/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaplatos.entity.Ingrediente;

/**
 * @author Edwin
 *
 */
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{
	
}
