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
	
	List<MenuGenerado> findByUltimoMenu(Persona persona);
}
