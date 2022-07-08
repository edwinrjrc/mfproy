/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class PlatoIngredienteExportDto extends PlatoIngredienteDto {

	private static final long serialVersionUID = 2254279229088250997L;

	private Double totalIngrediente;
	
	private Integer posicion;
	
	private String nroIngrediente;
	private String descripcionIngrediente;
	private String cantidadIngrediente;
	private String descripcionUnidadMedida;
	
	
	/**
	 * @return the totalIngrediente
	 */
	public Double getTotalIngrediente() {
		return totalIngrediente;
	}

	/**
	 * @param totalIngrediente the totalIngrediente to set
	 */
	public void setTotalIngrediente(Double totalIngrediente) {
		this.totalIngrediente = totalIngrediente;
	}

	/**
	 * @return the posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the nroIngrediente
	 */
	public String getNroIngrediente() {
		return nroIngrediente;
	}

	/**
	 * @param nroIngrediente the nroIngrediente to set
	 */
	public void setNroIngrediente(String nroIngrediente) {
		this.nroIngrediente = nroIngrediente;
	}

	/**
	 * @return the descripcionIngrediente
	 */
	public String getDescripcionIngrediente() {
		return descripcionIngrediente;
	}

	/**
	 * @param descripcionIngrediente the descripcionIngrediente to set
	 */
	public void setDescripcionIngrediente(String descripcionIngrediente) {
		this.descripcionIngrediente = descripcionIngrediente;
	}

	/**
	 * @return the cantidadIngrediente
	 */
	public String getCantidadIngrediente() {
		return cantidadIngrediente;
	}

	/**
	 * @param cantidadIngrediente the cantidadIngrediente to set
	 */
	public void setCantidadIngrediente(String cantidadIngrediente) {
		this.cantidadIngrediente = cantidadIngrediente;
	}

	/**
	 * @return the descripcionUnidadMedida
	 */
	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	/**
	 * @param descripcionUnidadMedida the descripcionUnidadMedida to set
	 */
	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}
	
	
}
