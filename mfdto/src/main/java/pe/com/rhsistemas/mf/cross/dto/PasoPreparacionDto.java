/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class PasoPreparacionDto  extends BaseDto{

	private static final long serialVersionUID = 3312480600442079735L;
	private int idPaso;
	private int idPlato;
	private String descripcioPaso;
	private int tiempoMinutos;
	private boolean usaFuego;
	private List<IngredienteDto> ingredientes;
	
	
	/**
	 * @return the idPaso
	 */
	public int getIdPaso() {
		return idPaso;
	}
	/**
	 * @param idPaso the idPaso to set
	 */
	public void setIdPaso(int idPaso) {
		this.idPaso = idPaso;
	}
	/**
	 * @return the descripcioPaso
	 */
	public String getDescripcioPaso() {
		return descripcioPaso;
	}
	/**
	 * @param descripcioPaso the descripcioPaso to set
	 */
	public void setDescripcioPaso(String descripcioPaso) {
		this.descripcioPaso = descripcioPaso;
	}
	/**
	 * @return the tiempoMinutos
	 */
	public int getTiempoMinutos() {
		return tiempoMinutos;
	}
	/**
	 * @param tiempoMinutos the tiempoMinutos to set
	 */
	public void setTiempoMinutos(int tiempoMinutos) {
		this.tiempoMinutos = tiempoMinutos;
	}
	/**
	 * @return the usaFuego
	 */
	public boolean isUsaFuego() {
		return usaFuego;
	}
	/**
	 * @param usaFuego the usaFuego to set
	 */
	public void setUsaFuego(boolean usaFuego) {
		this.usaFuego = usaFuego;
	}
	/**
	 * @return the ingredientes
	 */
	public List<IngredienteDto> getIngredientes() {
		return ingredientes;
	}
	/**
	 * @param ingredientes the ingredientes to set
	 */
	public void setIngredientes(List<IngredienteDto> ingredientes) {
		this.ingredientes = ingredientes;
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
