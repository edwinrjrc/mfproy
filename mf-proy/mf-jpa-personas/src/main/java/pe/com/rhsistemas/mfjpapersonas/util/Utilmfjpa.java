/**
 * 
 */
package pe.com.rhsistemas.mfjpapersonas.util;

import java.util.ArrayList;
import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.PersonaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaJuridicaDto;
import pe.com.rhsistemas.mf.cross.dto.PersonaNaturalDto;
import pe.com.rhsistemas.mf.cross.dto.RolDto;
import pe.com.rhsistemas.mf.cross.dto.UsuarioDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpapersonas.entity.Persona;
import pe.com.rhsistemas.mfjpapersonas.entity.PersonaJuridica;
import pe.com.rhsistemas.mfjpapersonas.entity.PersonaNatural;
import pe.com.rhsistemas.mfjpapersonas.entity.Usuario;
import pe.com.rhsistemas.mfjpapersonas.entity.UsuariosRole;
import pe.com.rhsistemas.mfjpapersonas.entity.UsuariosRolePK;

/**
 * @author Edwin
 *
 */
public class Utilmfjpa {

	public static PersonaNatural parsePersonaNaturalDto(PersonaNaturalDto dto) throws UtilMfDtoException {
		PersonaNatural entity = new PersonaNatural();
		if (dto != null) {
			entity.setIdPersona(dto.getId());
			entity.setNoPersona(dto.getNombres());
			entity.setTxPrimApel(dto.getPrimerApellido());
			entity.setTxSeguApel(dto.getSegundoApellido());
			entity.setDeCorreo(dto.getCorreoElectronico());
			entity.setNuTele1(dto.getTelefono1());
			entity.setNuTele2(dto.getTelefono2());
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setFeRegistro(UtilMfDto.parseDateASqlTimestamp(dto.getFechaRegistro()));
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setFeModificacion(UtilMfDto.parseDateASqlTimestamp(dto.getFechaModificacion()));
			entity.setPersona(parsePersonaDto(dto));
		}
		
		return entity;
	}
	
	public static PersonaJuridica parsePersonaJuridicaDto(PersonaJuridicaDto dto) throws UtilMfDtoException {
		PersonaJuridica entity = new PersonaJuridica();
		if (dto != null) {
			entity.setIdPersona(dto.getId());
			entity.setTxNombCome(dto.getNombreComercial());
			entity.setTxRazoSoci(dto.getRazonSocial());
			entity.setPersona(parsePersonaDto(dto));
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setFeRegistro(UtilMfDto.parseDateASqlTimestamp(dto.getFechaRegistro()));
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setFeModificacion(UtilMfDto.parseDateASqlTimestamp(dto.getFechaModificacion()));
		}
		
		return entity;
	}
	
	
	public static Persona parsePersonaDto(PersonaDto dto) throws UtilMfDtoException {
		Persona entity = new Persona();
		
		if (dto != null) {
			if (dto.getDocumentoIdentidad().getTipoDocumento() != null && dto.getDocumentoIdentidad().getTipoDocumento().getCodigo() !=null) {
				entity.setIdTipoDocu(UtilMfDto.parseStringAInteger(dto.getDocumentoIdentidad().getTipoDocumento().getCodigo()));
			}
			entity.setNuDocumento(dto.getDocumentoIdentidad().getNumeroDocumento());
			entity.setFeNacimiento(dto.getFechaNacimiento());
			entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
			entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
			entity.setFeRegistro(dto.getFechaRegistro());
			entity.setFeModificacion(dto.getFechaModificacion());
		}
		
		return entity;
	}
	
	
	
	public static Usuario paseUsuario(UsuarioDto dto) {
		Usuario entity = new Usuario();
		
		entity.setTxLogin(dto.getLogin());
		entity.setTxPassword(dto.getPassword());
		entity.setIdPersona(dto.getIdPersona());
		entity.setFeCaduca(dto.getFechaCaduca());
		entity.setFeModificacion(dto.getFechaModificacion());
		entity.setFeRegistro(dto.getFechaRegistro());
		entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
		entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
		entity.setInCuenCadu(dto.getInCuentaCaduca());
		entity.setInEstado(dto.getEstado());
		entity.setFeUltiActuPass(dto.getFechaUltActualizaPass());
		
		List<UsuariosRole> listaUsuariosRole = new ArrayList<>();
		for(int i=0; i<dto.getListaRoles().size(); i++) {
			RolDto rolDto = dto.getListaRoles().get(i);
			listaUsuariosRole.add(parseUsuariosRole (rolDto, dto.getIdPersona()));
		}
		entity.setUsuariosRoles(listaUsuariosRole);
		
		return entity;
	}
	
	public static UsuariosRole parseUsuariosRole(RolDto rolDto, Long idPersona) {
		UsuariosRole usuariosRole = new UsuariosRole();
		
		usuariosRole.setFeModificacion(rolDto.getFechaModificacion());
		usuariosRole.setFeRegistro(rolDto.getFechaRegistro());
		UsuariosRolePK usuarioRolePk = new UsuariosRolePK();
		usuarioRolePk.setRolesIdRol(rolDto.getIdrol());
		usuarioRolePk.setUsuariosIdPersona(idPersona);
		usuariosRole.setId(usuarioRolePk);
		usuariosRole.setIdUsuaCrea(rolDto.getIdUsuarioRegistro());
		usuariosRole.setIdUsuaModi(rolDto.getIdUsuarioModificacion());
		
		return usuariosRole;
	}
	
	public static PersonaNaturalDto parsePersonaNatural(Persona entity) {
		PersonaNaturalDto dto = new PersonaNaturalDto();
		
		dto.setCorreoElectronico(entity.getPersonaNatural().getDeCorreo());
		if (entity.getIdTipoDocu() != null) {
			dto.getDocumentoIdentidad().getTipoDocumento().setCodigo(entity.getIdTipoDocu().toString());
		}
		dto.getDocumentoIdentidad().setNumeroDocumento(entity.getNuDocumento());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaNacimiento(entity.getFeNacimiento());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.setId(entity.getIdPersona());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		dto.setNombres(entity.getPersonaNatural().getNoPersona());
		dto.setPrimerApellido(entity.getPersonaNatural().getTxPrimApel());
		dto.setSegundoApellido(entity.getPersonaNatural().getTxSeguApel());
		dto.setTelefono1(entity.getPersonaNatural().getNuTele1());
		dto.setTelefono2(entity.getPersonaNatural().getNuTele2());
		
		return dto;
	}
	
}
