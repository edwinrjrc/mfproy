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


/**
 * @author Edwin
 *
 */
public interface MenuRepository extends JpaRepository<MenuGenerado, Long> {

	@Query(value = "select M from MenuDetalle M where M.id.idGenerado = (select max(G.idGenerado) from MenuGenerado G where G.idPersona = ?1)")
	List<MenuDetalle> buscarUltimoMenuGenerado(Long idPersona);
	
	@Query(value = "select M from MenuDetalle M where M.id.idGenerado in (select G.idGenerado from MenuGenerado G where G.idPersona = ?1 and fe_generado >= ?2)")
	List<MenuDetalle> ultimosMenusRango(Long idPersona, Date fechaAnterior);
	
	@Query(value = "SELECT g FROM MenuGenerado g WHERE g.idGenerado = (SELECT MAX(m.idGenerado) FROM MenuGenerado m WHERE m.idPersona = ?1)")
	List<MenuGenerado> findByUltimoMenu(Long idPersona);
	
	@Query(value = "SELECT g FROM MenuGenerado g WHERE g.feGenerado = ?1")
	List<MenuGenerado> findByFechaGenerado(Date fechaGenerado);
}
