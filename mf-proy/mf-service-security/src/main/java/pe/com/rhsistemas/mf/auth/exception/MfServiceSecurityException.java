/**
 * 
 */
package pe.com.rhsistemas.mf.auth.exception;

/**
 * @author Edwin
 *
 */
public class MfServiceSecurityException extends Exception {
	
	private String cuerpoMensaje;

	private static final long serialVersionUID = 1462134173204377986L;

	/**
	 * 
	 */
	public MfServiceSecurityException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MfServiceSecurityException(String message, String cuerpoMensaje) {
		super(message);
		this.cuerpoMensaje = cuerpoMensaje;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MfServiceSecurityException(Throwable cause, String cuerpoMensaje) {
		super(cause);
		this.cuerpoMensaje = cuerpoMensaje;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MfServiceSecurityException(String message, Throwable cause, String cuerpoMensaje) {
		super(message, cause);
		this.cuerpoMensaje = cuerpoMensaje;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MfServiceSecurityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cuerpoMensaje
	 */
	public String getCuerpoMensaje() {
		return cuerpoMensaje;
	}

}
