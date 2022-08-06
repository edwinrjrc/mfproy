/**
 * 
 */
package pe.com.rhsistemas.mf.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Edwin
 *
 */
public class AutenticacionException extends AuthenticationException {
	
	private static final long serialVersionUID = 5497627465606122676L;
	
	public AutenticacionException(String msg, HttpStatus status) {
		super(msg);
	}
	
}
