/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class ConfiguracionCuentaDto extends BaseDto {

	private static final long serialVersionUID = -9110738584240284415L;
	
	private String nombreFamilia;
	private Integer numeroHombres;
	private Integer numeroMujeres;
	private String diasCocinaSemana;
	private Long idPersona;

	/**
	 * 
	 */
	public ConfiguracionCuentaDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nombreFamilia
	 */
	public String getNombreFamilia() {
		return nombreFamilia;
	}

	/**
	 * @param nombreFamilia the nombreFamilia to set
	 */
	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

	/**
	 * @return the numeroHombres
	 */
	public Integer getNumeroHombres() {
		return numeroHombres;
	}

	/**
	 * @param numeroHombres the numeroHombres to set
	 */
	public void setNumeroHombres(Integer numeroHombres) {
		this.numeroHombres = numeroHombres;
	}

	/**
	 * @return the numeroMujeres
	 */
	public Integer getNumeroMujeres() {
		return numeroMujeres;
	}

	/**
	 * @param numeroMujeres the numeroMujeres to set
	 */
	public void setNumeroMujeres(Integer numeroMujeres) {
		this.numeroMujeres = numeroMujeres;
	}

	/**
	 * @return the diasCocinaSemana
	 */
	public String getDiasCocinaSemana() {
		return diasCocinaSemana;
	}

	/**
	 * @param diasCocinaSemana the diasCocinaSemana to set
	 */
	public void setDiasCocinaSemana(String diasCocinaSemana) {
		this.diasCocinaSemana = diasCocinaSemana;
	}

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

}
