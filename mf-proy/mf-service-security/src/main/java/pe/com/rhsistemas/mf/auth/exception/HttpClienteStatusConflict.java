/**
 * 
 */
package pe.com.rhsistemas.mf.auth.exception;

/**
 * @author Edwin
 *
 */
public class HttpClienteStatusConflict extends MfServiceSecurityException {

	private static final long serialVersionUID = -3104930161781902204L;

	/**
	 * 
	 */
	public HttpClienteStatusConflict() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public HttpClienteStatusConflict(String message, String cuerpoMensaje) {
		super(message,cuerpoMensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public HttpClienteStatusConflict(Throwable cause, String cuerpoMensaje) {
		super(cause, cuerpoMensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpClienteStatusConflict(String message, Throwable cause, String cuerpoMensaje) {
		super(message, cause, cuerpoMensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HttpClienteStatusConflict(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
