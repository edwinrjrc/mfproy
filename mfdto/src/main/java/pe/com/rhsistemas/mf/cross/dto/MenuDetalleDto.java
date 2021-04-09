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
	private int idPlato;

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
	 * @return the idPlato
	 */
	public int getIdPlato() {
		return idPlato;
	}

	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}

}
