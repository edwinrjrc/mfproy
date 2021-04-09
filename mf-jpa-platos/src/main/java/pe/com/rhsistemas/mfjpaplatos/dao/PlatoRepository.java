/**
 * 
 */
package pe.com.rhsistemas.mfjpaplatos.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpaplatos.entity.Plato;

/**
 * @author Edwin
 *
 */
public interface PlatoRepository extends JpaRepository<Plato, Integer>{

	@Query(value = "select p.* from sistema.plato p where id_plato not in (select d.id_plato from sistema.menu_detalle d "
			+ "where d.id_generado in (select m.id_generado where sistema.menu_generado m where m.id_persona = ?1 and m.feGenerado >= ?2))", nativeQuery = true)
	List<Plato> platosNoConsumidos(Integer idPersona, Date fechaCorte);
}
