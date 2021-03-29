/**
 * 
 */
package pe.com.rhsistemas.mfjpamenu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpamenu.entity.MenuGenerado;

/**
 * @author Edwin
 *
 */
public interface MenuRepository extends JpaRepository<MenuGenerado, Long> {

}
