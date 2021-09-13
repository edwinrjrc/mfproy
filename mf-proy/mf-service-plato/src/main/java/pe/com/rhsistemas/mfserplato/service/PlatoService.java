/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service;

import java.util.List;

import pe.com.rhsistemas.mf.cross.dto.PlatoDto;
import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;

/**
 * @author Edwin
 *
 */
public interface PlatoService {

	public void guardarPlatoFavorito(PlatoFavoritoDto platoFavoritoDto) throws MFServicePlatoException;

	List<PlatoDto> listarPlatos() throws MFServicePlatoException;
}
