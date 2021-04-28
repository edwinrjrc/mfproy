/**
 * 
 */
package pe.com.rhsistemas.mfserplatos.service;

import pe.com.rhsistemas.mf.cross.dto.MenuGeneradoDto;
import pe.com.rhsistemas.mfserplatos.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
public interface MenuLogicaService {

	public void generarMenu(Integer idPersona, Integer idUsuario) throws MfServiceMenuException;
	
	public MenuGeneradoDto consultarMenuActivo(Integer idPersona) throws MfServiceMenuException;
}
