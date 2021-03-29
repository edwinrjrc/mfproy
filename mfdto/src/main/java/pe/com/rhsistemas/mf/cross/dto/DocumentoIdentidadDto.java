/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class DocumentoIdentidadDto extends BaseDto {

	private static final long serialVersionUID = 3236794276451039145L;
	
	/**
	 * Tipo de documento usando el objeto BaseValor
	 */
	private BaseValor tipoDocumento;
	/**
	 * Numero de documento
	 */
	private String numeroDocumento;
	
	
	/**
	 * @return the tipoDocumento
	 */
	public BaseValor getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(BaseValor tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	
}
