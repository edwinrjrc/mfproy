/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaplatos.entity.Receta;
import pe.com.rhsistemas.mfjpaplatos.entity.RecetaPK;

/**
 * @author Edwin
 *
 */
public interface RecetaRepository extends JpaRepository<Receta, RecetaPK>{

}
