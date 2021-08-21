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
	private String fechaConsumoStr;
	private String nombreDia;
	private Integer numSemana;
	private PlatoDto platoDto;
	private Integer idTipoPlato;
	private Integer idGenerado;

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
	public Integer getNumSemana() {
		return numSemana;
	}

	/**
	 * @param numSemana the numSemana to set
	 */
	public void setNumSemana(Integer numSemana) {
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

	/**
	 * @return the idTipoPlato
	 */
	public Integer getIdTipoPlato() {
		return idTipoPlato;
	}

	/**
	 * @param idTipoPlato the idTipoPlato to set
	 */
	public void setIdTipoPlato(Integer idTipoPlato) {
		this.idTipoPlato = idTipoPlato;
	}

	/**
	 * @return the idGenerado
	 */
	public Integer getIdGenerado() {
		return idGenerado;
	}

	/**
	 * @param idGenerado the idGenerado to set
	 */
	public void setIdGenerado(Integer idGenerado) {
		this.idGenerado = idGenerado;
	}

	/**
	 * @return the fechaConsumoStr
	 */
	public String getFechaConsumoStr() {
		return fechaConsumoStr;
	}

	/**
	 * @param fechaConsumoStr the fechaConsumoStr to set
	 */
	public void setFechaConsumoStr(String fechaConsumoStr) {
		this.fechaConsumoStr = fechaConsumoStr;
	}

}
