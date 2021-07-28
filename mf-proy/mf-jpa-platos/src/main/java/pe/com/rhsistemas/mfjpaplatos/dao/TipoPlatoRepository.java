/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaplatos.entity.TipoPlato;

/**
 * @author Edwin
 *
 */
public interface TipoPlatoRepository extends JpaRepository<TipoPlato, Integer> {

	
	List<TipoPlato> findByInEntrada(String inEntrada);
	
	List<TipoPlato> findByInFondo(String inFondo);
}
