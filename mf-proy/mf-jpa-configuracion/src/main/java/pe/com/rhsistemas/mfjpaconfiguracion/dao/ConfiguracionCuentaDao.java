/**
 * 
 */
package pe.com.rhsistemas.mfjpaconfiguracion.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.rhsistemas.mfjpaconfiguracion.entity.ConfiguracionFamilia;

/**
 * @author Edwin
 *
 */
public interface ConfiguracionCuentaDao extends JpaRepository<ConfiguracionFamilia, Long> {

}
