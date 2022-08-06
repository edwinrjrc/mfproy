/**
 * 
 */
package pe.com.rhsistemas.mfservicemail.service;

import pe.com.rhsistemas.mf.post.dto.DatosCorreo;
import pe.com.rhsistemas.mfservicemail.exception.MfServiceMailException;

/**
 * @author Edwin
 *
 */
public interface CorreoService {

	void enviarCorreoSinAdjunto(DatosCorreo datosCorreo) throws MfServiceMailException;
}
