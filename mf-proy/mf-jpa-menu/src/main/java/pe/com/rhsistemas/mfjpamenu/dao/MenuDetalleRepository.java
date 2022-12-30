/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import pe.com.rhsistemas.mfjpamenu.entity.MenuDetalle;
import pe.com.rhsistemas.mfjpamenu.entity.MenuDetallePK;

/**
 * @author Edwin
 *
 */
@Transactional
public interface MenuDetalleRepository extends JpaRepository<MenuDetalle, MenuDetallePK>{

	@Query(value = "select d from MenuDetalle d where d.id.idGenerado = ?1")
	List<MenuDetalle> findByMenuGenerado(Long idGenerado);
	
	@Modifying
	@Query(value = "update MenuDetalle set idPlato = ?1, feModificacion = current_timestamp where id = ?2")
	public void actualizaMenuDia(Integer idPlato, MenuDetallePK menuDetallePk);
}
