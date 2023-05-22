/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

import java.io.Serializable;

/**
 * @author Edwin
 *
 */
public class DatosCorreo implements Serializable{
	
	
	private static final long serialVersionUID = -2552049760449252260L;
	
	private String asunto;
	private String mensaje;
	private byte[] adjunto;
	private String nombreAdjunto;
	private String tipoContenidoAdjunto;
	private String para;
	
	
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
	/**
	 * @return the adjunto
	 */
	public byte[] getAdjunto() {
		return adjunto;
	}
	/**
	 * @param adjunto the adjunto to set
	 */
	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}
	/**
	 * @return the nombreAdjunto
	 */
	public String getNombreAdjunto() {
		return nombreAdjunto;
	}
	/**
	 * @param nombreAdjunto the nombreAdjunto to set
	 */
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}
	/**
	 * @return the tipoContenidoAdjunto
	 */
	public String getTipoContenidoAdjunto() {
		return tipoContenidoAdjunto;
	}
	/**
	 * @param tipoContenidoAdjunto the tipoContenidoAdjunto to set
	 */
	public void setTipoContenidoAdjunto(String tipoContenidoAdjunto) {
		this.tipoContenidoAdjunto = tipoContenidoAdjunto;
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
	
	

}
