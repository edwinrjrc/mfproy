/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpapersonas.entity.Persona;

/**
 * @author Edwin
 *
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
