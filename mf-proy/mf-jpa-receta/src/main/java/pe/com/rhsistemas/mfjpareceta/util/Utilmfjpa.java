package pe.com.rhsistemas.mfjpareceta.util;
/**
 * 
 */

import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
import pe.com.rhsistemas.mfjpareceta.entity.Receta;

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
	
}
