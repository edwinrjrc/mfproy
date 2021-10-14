/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class RecetaDto extends BaseDto {

	private static final long serialVersionUID = 1882346425055623323L;

	private Integer idPaso;
	private Integer idPlato;
	private String descripcionReceta;
	private String indicadorCoccion;
	private Double minutosCompletar;
	
	
	/**
	 * @return the idPaso
	 */
	public Integer getIdPaso() {
		return idPaso;
	}
	/**
	 * @param idPaso the idPaso to set
	 */
	public void setIdPaso(Integer idPaso) {
		this.idPaso = idPaso;
	}
	/**
	 * @return the idPlato
	 */
	public Integer getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	/**
	 * @return the descripcionReceta
	 */
	public String getDescripcionReceta() {
		return descripcionReceta;
	}
	/**
	 * @param descripcionReceta the descripcionReceta to set
	 */
	public void setDescripcionReceta(String descripcionReceta) {
		this.descripcionReceta = descripcionReceta;
	}
	/**
	 * @return the indicadorCoccion
	 */
	public String getIndicadorCoccion() {
		return indicadorCoccion;
	}
	/**
	 * @param indicadorCoccion the indicadorCoccion to set
	 */
	public void setIndicadorCoccion(String indicadorCoccion) {
		this.indicadorCoccion = indicadorCoccion;
	}
	/**
	 * @return the minutosCompletar
	 */
	public Double getMinutosCompletar() {
		return minutosCompletar;
	}
	/**
	 * @param minutosCompletar the minutosCompletar to set
	 */
	public void setMinutosCompletar(Double minutosCompletar) {
		this.minutosCompletar = minutosCompletar;
	}
	
	
}
