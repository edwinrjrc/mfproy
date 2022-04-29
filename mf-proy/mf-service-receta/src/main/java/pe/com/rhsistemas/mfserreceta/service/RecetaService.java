/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.post.dto.RecetaComentarioPostDto;
import pe.com.rhsistemas.mfserreceta.exception.MfServiceRecetaException;

/**
 * @author Edwin
 *
 */
public interface RecetaService {

	void guardarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException;

	void eliminarComentarioReceta(RecetaComentarioPostDto recetaComentarioDto) throws MfServiceRecetaException;

	List<RecetaComentarioDto> consultarComentario(Integer idPlato) throws MfServiceRecetaException;

}
