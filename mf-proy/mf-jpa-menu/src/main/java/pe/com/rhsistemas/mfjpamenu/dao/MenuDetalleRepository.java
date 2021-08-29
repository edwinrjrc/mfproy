/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetallePK;

/**
 * @author Edwin
 *
 */
public interface MenuDetalleRepository extends JpaRepository<MenuDetalle, MenuDetallePK>{

	@Query(value = "select d from MenuDetalle d where d.id.idGenerado = ?1")
	List<MenuDetalle> findByMenuGenerado(Long idGenerado);
	
}
