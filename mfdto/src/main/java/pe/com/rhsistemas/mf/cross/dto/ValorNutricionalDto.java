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
	
	private int idIngrediente;
	private int idTipoCompo;
	private float nuAporte;
	
	
	/**
	 * @return the idIngrediente
	 */
	public int getIdIngrediente() {
		return idIngrediente;
	}
	/**
	 * @param idIngrediente the idIngrediente to set
	 */
	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	/**
	 * @return the idTipoCompo
	 */
	public int getIdTipoCompo() {
		return idTipoCompo;
	}
	/**
	 * @param idTipoCompo the idTipoCompo to set
	 */
	public void setIdTipoCompo(int idTipoCompo) {
		this.idTipoCompo = idTipoCompo;
	}
	/**
	 * @return the nuAporte
	 */
	public float getNuAporte() {
		return nuAporte;
	}
	/**
	 * @param nuAporte the nuAporte to set
	 */
	public void setNuAporte(float nuAporte) {
		this.nuAporte = nuAporte;
	}

	
	
}
