/**
 * 
 */
package pe.com.rhsistemas.mfserreceta.service;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.IngredientesPlatoCargaDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaComentarioDto;
import pe.com.rhsistemas.mf.cross.dto.RecetaDto;
import pe.com.rhsistemas.mf.cross.exception.UtilMfDtoException;
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

	void guardarIngredientesPreparacionPlato(List<IngredientesPlatoCargaDto> listaIngredientesCarga, List<RecetaDto> listaPasosReceta) throws MfServiceRecetaException, UtilMfDtoException;

	void guardarIngredientesPlato(List<IngredientesPlatoCargaDto> listaIngredientesCarga) throws MfServiceRecetaException;

	void guardarPreparacionPlato(List<RecetaDto> listaPasosReceta) throws MfServiceRecetaException;

	void eliminarIngredientesPlato(Integer idPlato) throws MfServiceRecetaException;

	void eliminarPreparacionPlato(Integer idPlato) throws MfServiceRecetaException;

}
