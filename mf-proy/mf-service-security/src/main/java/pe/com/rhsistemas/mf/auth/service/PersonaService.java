/**
 * 
 */
package pe.com.rhsistemas.mf.auth.service;

import pe.com.rhsistemas.mf.auth.exception.MfServiceSecurityException;
import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;

/**
 * @author Edwin
 *
 */
public interface PersonaService {

	PersonaDto registrarPersonaNatural(PersonaNaturalDto dto) throws MfServiceSecurityException;
	
	PersonaDto registrarPersonaJuridica(PersonaNaturalDto dto);
	
	void registrarUsuario(UsuarioDto dto) throws MfServiceSecurityException;

}
