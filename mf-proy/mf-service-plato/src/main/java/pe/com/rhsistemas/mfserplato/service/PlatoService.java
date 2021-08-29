/**
 * 
 */
package pe.com.rhsistemas.mfserplato.service;

import org.springframework.stereotype.Service;

import pe.com.rhsistemas.mf.cross.dto.PlatoFavoritoDto;
import pe.com.rhsistemas.mfserplato.exception.MFServicePlatoException;

/**
 * @author Edwin
 *
 */
public interface PlatoService {

	public void guardarPlatoFavorito(PlatoFavoritoDto platoFavoritoDto) throws MFServicePlatoException;
}
