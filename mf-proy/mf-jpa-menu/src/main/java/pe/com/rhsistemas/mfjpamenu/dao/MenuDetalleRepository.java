/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetallePK;
import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;

/**
 * @author Edwin
 *
 */
public interface MenuDetalleRepository extends JpaRepository<MenuDetalle, MenuDetallePK>{

	List<MenuDetalle> findByMenuGenerado(MenuGenerado menuGenerado);
}
