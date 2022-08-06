/**
 * 
 */
package pe.com.rhsistemas.mf.cross.util;

import javax.mail.Session;

/**
 * @author Edwin
 *
 */
public class UtilCorreo {

	private String asunto;
	private String para;
	private String mensaje;
	
	private String host;
	private String protocol;
	private String user;
	private String password;
	
	Session mailSession = null;
	/**
	 * 
	 */
	public UtilCorreo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
