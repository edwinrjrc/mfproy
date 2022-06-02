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
	
	
}
