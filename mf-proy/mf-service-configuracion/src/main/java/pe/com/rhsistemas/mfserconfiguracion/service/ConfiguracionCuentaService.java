/**
 * 
 */
package pe.com.rhsistemas.mfserconfiguracion.service;

import pe.com.rhsistemas.mf.cross.dto.ConfiguracionCuentaDto;
import pe.com.rhsistemas.mfserconfiguracion.exception.MfServiceConfiguracionException;

/**
 * @author Edwin
 *
 */
public interface ConfiguracionCuentaService {

	public void guardaConfiguracionCuenta(ConfiguracionCuentaDto configuracion) throws MfServiceConfiguracionException;

	ConfiguracionCuentaDto consultarConfiguracionFamilia(Integer idPersona) throws MfServiceConfiguracionException;
}
