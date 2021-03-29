/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.Date;

/**
 * @author Edwin
 *
 */
public class PersonaDto extends BaseDto {

	private static final long serialVersionUID = 7453751054913773574L;
	
	/**
	 * Identificador unico de la persona
	 */
	private long id;
	/**
	 * Documento de ldentidad de la persona que es tipo y numero
	 */
	private DocumentoIdentidadDto documentoIdentidad;
	/**
	 * Fecha de nacimiento de la persona N o J
	 */
	private Date fechaNacimiento;
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the documentoIdentidad
	 */
	public DocumentoIdentidadDto getDocumentoIdentidad() {
		if (documentoIdentidad == null) {
			documentoIdentidad = new DocumentoIdentidadDto();
		}
		return documentoIdentidad;
	}
	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(DocumentoIdentidadDto documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
