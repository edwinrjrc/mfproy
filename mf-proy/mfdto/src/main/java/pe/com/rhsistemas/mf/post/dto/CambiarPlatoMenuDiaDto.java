/**
 * 
 */
package pe.com.rhsistemas.mf.post.dto;

/**
 * @author Edwin
 *
 */
public class CambiarPlatoMenuDiaDto {

	private String fechaConsumo;
	private Long idMenu;
	private Long idPlato;
	
	
	/**
	 * @return the fechaConsumo
	 */
	public String getFechaConsumo() {
		return fechaConsumo;
	}
	/**
	 * @param fechaConsumo the fechaConsumo to set
	 */
	public void setFechaConsumo(String fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}
	/**
	 * @return the idMenu
	 */
	public Long getIdMenu() {
		return idMenu;
	}
	/**
	 * @param idMenu the idMenu to set
	 */
	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
	/**
	 * @return the idPlato
	 */
	public Long getIdPlato() {
		return idPlato;
	}
	/**
	 * @param idPlato the idPlato to set
	 */
	public void setIdPlato(Long idPlato) {
		this.idPlato = idPlato;
	}
}
