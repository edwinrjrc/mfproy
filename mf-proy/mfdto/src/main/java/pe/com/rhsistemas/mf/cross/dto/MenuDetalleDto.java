/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.Date;

/**
 * @author Edwin
 *
 */
public class MenuDetalleDto extends BaseDto {

	private static final long serialVersionUID = 3549472290139151728L;
	
	private Date fechaConsumo;
	private String nombreDia;
	private int numSemana;
	private PlatoDto platoDto;

	public MenuDetalleDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fechaConsumo
	 */
	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	/**
	 * @param fechaConsumo the fechaConsumo to set
	 */
	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	/**
	 * @return the platoDto
	 */
	public PlatoDto getPlatoDto() {
		if (platoDto == null) {
			platoDto = new PlatoDto();
		}
		return platoDto;
	}

	/**
	 * @param platoDto the platoDto to set
	 */
	public void setPlatoDto(PlatoDto platoDto) {
		this.platoDto = platoDto;
	}

	/**
	 * @return the numSemana
	 */
	public int getNumSemana() {
		return numSemana;
	}

	/**
	 * @param numSemana the numSemana to set
	 */
	public void setNumSemana(int numSemana) {
		this.numSemana = numSemana;
	}

	/**
	 * @return the nombreDia
	 */
	public String getNombreDia() {
		return nombreDia;
	}

	/**
	 * @param nombreDia the nombreDia to set
	 */
	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

}
