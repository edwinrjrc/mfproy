/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class ValorNutricionalDto extends BaseDto {

	private static final long serialVersionUID = -7387741760977073646L;
	
	private Integer idIngrediente;
	private Integer idTipoCompo;
	private Float nuAporte;
	
	
	/**
	 * @return the idIngrediente
	 */
	public Integer getIdIngrediente() {
		return idIngrediente;
	}
	/**
	 * @param idIngrediente the idIngrediente to set
	 */
	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	/**
	 * @return the idTipoCompo
	 */
	public Integer getIdTipoCompo() {
		return idTipoCompo;
	}
	/**
	 * @param idTipoCompo the idTipoCompo to set
	 */
	public void setIdTipoCompo(Integer idTipoCompo) {
		this.idTipoCompo = idTipoCompo;
	}
	/**
	 * @return the nuAporte
	 */
	public Float getNuAporte() {
		return nuAporte;
	}
	/**
	 * @param nuAporte the nuAporte to set
	 */
	public void setNuAporte(Float nuAporte) {
		this.nuAporte = nuAporte;
	}

}
