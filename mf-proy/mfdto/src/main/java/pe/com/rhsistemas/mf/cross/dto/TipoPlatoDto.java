/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class TipoPlatoDto extends BaseDto{

	private static final long serialVersionUID = 3657620069454013366L;

	private Byte idTipo;
	private String descTipoPlato;
	private String indicadorEntrada;
	private String indicadorFondo;
	
	
	/**
	 * @return the idTipo
	 */
	public Byte getIdTipo() {
		return idTipo;
	}
	/**
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(Byte idTipo) {
		this.idTipo = idTipo;
	}
	/**
	 * @return the descTipoPlato
	 */
	public String getDescTipoPlato() {
		return descTipoPlato;
	}
	/**
	 * @param descTipoPlato the descTipoPlato to set
	 */
	public void setDescTipoPlato(String descTipoPlato) {
		this.descTipoPlato = descTipoPlato;
	}
	/**
	 * @return the indicadorEntrada
	 */
	public String getIndicadorEntrada() {
		return indicadorEntrada;
	}
	/**
	 * @param indicadorEntrada the indicadorEntrada to set
	 */
	public void setIndicadorEntrada(String indicadorEntrada) {
		this.indicadorEntrada = indicadorEntrada;
	}
	/**
	 * @return the indicadorFondo
	 */
	public String getIndicadorFondo() {
		return indicadorFondo;
	}
	/**
	 * @param indicadorFondo the indicadorFondo to set
	 */
	public void setIndicadorFondo(String indicadorFondo) {
		this.indicadorFondo = indicadorFondo;
	}
	
}
