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
	
	private Integer id;
	private String nombreIngrediente;
	private Float numeroCalorias;
	private Float numeroGrasas;
	private Float numeroProteina;
	private BaseValor tipoIngrediente;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	public Float getNumeroCalorias() {
		return numeroCalorias;
	}
	/**
	 * @param numeroCalorias the numeroCalorias to set
	 */
	public void setNumeroCalorias(Float numeroCalorias) {
		this.numeroCalorias = numeroCalorias;
	}
	/**
	 * @return the numeroGrasas
	 */
	public Float getNumeroGrasas() {
		return numeroGrasas;
	}
	/**
	 * @param numeroGrasas the numeroGrasas to set
	 */
	public void setNumeroGrasas(Float numeroGrasas) {
		this.numeroGrasas = numeroGrasas;
	}
	/**
	 * @return the numeroProteina
	 */
	public Float getNumeroProteina() {
		return numeroProteina;
	}
	/**
	 * @param numeroProteina the numeroProteina to set
	 */
	public void setNumeroProteina(Float numeroProteina) {
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
