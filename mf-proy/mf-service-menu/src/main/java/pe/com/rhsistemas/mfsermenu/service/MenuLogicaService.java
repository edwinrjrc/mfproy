/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service;

import java.util.List;
import java.util.Map;

import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mfsermenu.exception.MfServiceMenuException;

/**
 * @author Edwin
 *
 */
public interface MenuLogicaService {

	public void generarMenu(Integer idPersona, Integer idUsuario) throws MfServiceMenuException, UtilMfDtoException;
	
	public List<Map<String, Object>> consultarMenuActivo(Integer idPersona) throws MfServiceMenuException;
}
