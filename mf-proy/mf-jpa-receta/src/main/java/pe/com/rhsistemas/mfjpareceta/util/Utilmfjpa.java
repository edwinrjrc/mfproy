package pe.com.rhsistemas.mfjpareceta.util;
/**
 * 
 */

import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mf.cross.util.UtilMfDto;
import pe.com.rhsistemas.mfjpareceta.entity.Receta;
import pe.com.rhsistemas.mfjpareceta.entity.RecetaComentario;

/**
 * @author Edwin
 *
 */
public class Utilmfjpa {
	
	public static RecetaDto parseReceta(Receta entity) throws UtilMfDtoException {
		RecetaDto dto = new RecetaDto();
		if (entity != null) {
			dto.setIdPaso(entity.getId().getIdPaso());
			dto.setIdPlato(entity.getId().getIdPlato());
			dto.setDescripcionReceta(entity.getDePasoRece());
			dto.setIndicadorCoccion(entity.getInPasoCocc().toString());
			dto.setMinutosCompletar(Double.valueOf(entity.getNuMinuComp()));
			dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
			dto.setFechaRegistro(entity.getFeRegistro());
			dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
			dto.setFechaModificacion(entity.getFeModificacion());
		}
		
		return dto;
	}
	
	public static RecetaComentario parseRecetaComentarioDto (RecetaComentarioDto dto) {
		RecetaComentario entity = new RecetaComentario();
		
		entity.setFeModificacion(UtilMfDto.parseDateASqlTimestamp(dto.getFechaModificacion()));
		entity.setFeRegistro(UtilMfDto.parseDateASqlTimestamp(dto.getFechaRegistro()));
		
		if (dto.getIdComentarioPlato() != null) {
			entity.setIdComePlat(Long.valueOf(dto.getIdComentarioPlato()));
		}
		entity.setIdPlato(dto.getIdPlato());
		entity.setIdUsuaCrea(dto.getIdUsuarioRegistro());
		entity.setIdUsuaModi(dto.getIdUsuarioModificacion());
		entity.setTxComentario(dto.getComentario());
		
		
		return entity;
	}
	
	public static RecetaComentarioDto parseRecetaComentario(RecetaComentario entity) {
		RecetaComentarioDto dto = new RecetaComentarioDto();
		
		dto.setComentario(entity.getTxComentario());
		dto.setFechaModificacion(entity.getFeModificacion());
		dto.setFechaRegistro(entity.getFeRegistro());
		dto.setIdComentarioPlato(entity.getIdComePlat().intValue());
		dto.setIdPlato(entity.getIdPlato());
		dto.setIdUsuarioModificacion(entity.getIdUsuaModi());
		dto.setIdUsuarioRegistro(entity.getIdUsuaCrea());
		
		return dto;
	}
	
}
