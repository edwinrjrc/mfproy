/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaplatos.entity.ValorNutricional;
import pe.com.rhsistemas.mfjpaplatos.entity.ValorNutricionalPK;

/**
 * @author Edwin
 *
 */
public interface ValorNutricionalRepository extends JpaRepository<ValorNutricional, ValorNutricionalPK>{

}
