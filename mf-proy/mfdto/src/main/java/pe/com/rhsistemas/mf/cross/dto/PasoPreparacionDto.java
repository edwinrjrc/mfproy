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
	private Integer idPaso;
	private Integer idPlato;
	private String descripcioPaso;
	private Integer tiempoMinutos;
	private Boolean usaFuego;
	private List<IngredienteDto> ingredientes;
	
	
	/**
	 * @return the idPaso
	 */
	public Integer getIdPaso() {
		return idPaso;
	}
	/**
	 * @param idPaso the idPaso to set
	 */
	public void setIdPaso(Integer idPaso) {
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
	public Integer getTiempoMinutos() {
		return tiempoMinutos;
	}
	/**
	 * @param tiempoMinutos the tiempoMinutos to set
	 */
	public void setTiempoMinutos(Integer tiempoMinutos) {
		this.tiempoMinutos = tiempoMinutos;
	}
	/**
	 * @return the usaFuego
	 */
	public Boolean isUsaFuego() {
		return usaFuego;
	}
	/**
	 * @param usaFuego the usaFuego to set
	 */
	public void setUsaFuego(Boolean usaFuego) {
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
	public Integer getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(Integer idPlato) {
		this.idPlato = idPlato;
	}
	
}
