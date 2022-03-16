/**
 * 
 */
package pe.com.rhsistemas.mf.auth.util;

import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;

/**
 * @author Edwin
 *
 */
public class Utilmfsecurity {

	public static Usuario parseUsuario(UsuarioDto user) {
		Usuario usuario = new Usuario();
		
		usuario.setFeCaduca(UtilMfDto.parseDateASqlTimestamp(user.getFechaCaduca()));
		usuario.setFeModificacion(UtilMfDto.parseDateASqlTimestamp(user.getFechaModificacion()));
		usuario.setFeRegistro(UtilMfDto.parseDateASqlTimestamp(user.getFechaRegistro()));
		usuario.setFeUltiActuPass(UtilMfDto.parseDateASqlTimestamp(user.getFechaUltActualizaPass()));
		usuario.setIdPersona(Long.valueOf(user.getIdPersona()));
		usuario.setIdUsuaCrea(user.getIdUsuarioRegistro());
		usuario.setIdUsuaModi(user.getIdUsuarioModificacion());
		usuario.setTxLogin(user.getLogin());
		usuario.setTxPassword(user.getPassword());
		
		return usuario;
	}
}
