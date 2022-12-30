/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.service;

import java.util.Date;
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
	
	public void cambiarPlatoDia(Integer idPersona, Integer idTipoPlato, Integer numeroDia) throws MfServiceMenuException;
	
	public void cambiarPlatoMenuDia(Long idMenu, Date fechaConsumo, Long idPlato) throws MfServiceMenuException;
}
