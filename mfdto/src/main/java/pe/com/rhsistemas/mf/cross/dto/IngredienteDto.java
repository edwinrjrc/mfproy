/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class IngredienteDto extends BaseDto {

	private static final long serialVersionUID = -2673013093232639773L;
	
	private int id;
	private String nombreIngrediente;
	private double numeroCalorias;
	private double numeroGrasas;
	private double numeroProteina;
	private BaseValor tipoIngrediente;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nombreIngrediente
	 */
	public String getNombreIngrediente() {
		return nombreIngrediente;
	}
	/**
	 * @param nombreIngrediente the nombreIngrediente to set
	 */
	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}
	/**
	 * @return the numeroCalorias
	 */
	public double getNumeroCalorias() {
		return numeroCalorias;
	}
	/**
	 * @param numeroCalorias the numeroCalorias to set
	 */
	public void setNumeroCalorias(double numeroCalorias) {
		this.numeroCalorias = numeroCalorias;
	}
	/**
	 * @return the numeroGrasas
	 */
	public double getNumeroGrasas() {
		return numeroGrasas;
	}
	/**
	 * @param numeroGrasas the numeroGrasas to set
	 */
	public void setNumeroGrasas(double numeroGrasas) {
		this.numeroGrasas = numeroGrasas;
	}
	/**
	 * @return the numeroProteina
	 */
	public double getNumeroProteina() {
		return numeroProteina;
	}
	/**
	 * @param numeroProteina the numeroProteina to set
	 */
	public void setNumeroProteina(double numeroProteina) {
		this.numeroProteina = numeroProteina;
	}
	/**
	 * @return the tipoIngrediente
	 */
	public BaseValor getTipoIngrediente() {
		return tipoIngrediente;
	}
	/**
	 * @param tipoIngrediente the tipoIngrediente to set
	 */
	public void setTipoIngrediente(BaseValor tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}
	
}
