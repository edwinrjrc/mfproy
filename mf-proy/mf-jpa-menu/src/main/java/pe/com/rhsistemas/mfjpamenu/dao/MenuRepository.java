/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;
import pe.com.rhsistemas.mfjpamenu.entity.Persona;


/**
 * @author Edwin
 *
 */
public interface MenuRepository extends JpaRepository<MenuGenerado, Long> {

	List<MenuGenerado> findByPersona(Persona persona);
	
	@Query(value = "select M from MenuDetalle M where M.menuGenerado = (select max(G.idGenerado) from MenuGenerado G where G.persona = ?1)")
	List<MenuDetalle> buscarUltimoMenuGenerado(Persona persona);
	
	@Query(value = "select M from MenuDetalle M where M.menuGenerado in (select G.idGenerado from MenuGenerado G where G.persona = ?1 and fe_generado >= ?2)")
	List<MenuDetalle> ultimosMenusRango(Persona persona, Date fechaAnterior);
	
	@Query(value = "SELECT g FROM MenuGenerado g WHERE g.idGenerado = (SELECT MAX(m.idGenerado) FROM MenuGenerado m WHERE m.persona = ?1)")
	List<MenuGenerado> findByUltimoMenu(Persona persona);
	
	@Query(value = "SELECT g FROM MenuGenerado g WHERE g.feGenerado = ?1")
	List<MenuGenerado> findByFechaGenerado(Date fechaGenerado);
}
