/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpapersonas.entity.SolicitudRecuperaCredencial;

/**
 * @author Edwin
 *
 */
public interface SolicitudRecuperoCredencialRepository extends JpaRepository<SolicitudRecuperaCredencial, Long> {

	@Query(value = "Select s from SolicitudRecuperaCredencial s where s.idSoliRecuCred = (select max(r.idSoliRecuCred) from SolicitudRecuperaCredencial r where r.idPersona = ?1)")
	SolicitudRecuperaCredencial buscarUltimaSolicitud(Long idPersona);
}
