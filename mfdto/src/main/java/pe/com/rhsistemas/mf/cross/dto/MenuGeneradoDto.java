/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.List;

/**
 * @author Edwin
 *
 */
public class MenuGeneradoDto extends BaseDto {
	
	private static final long serialVersionUID = 1440805495819436622L;
	
	private long idGenerado;
	private int numeroDias;
	private int idPersona;
	private List<MenuDetalleDto> listaPlatos;

	public MenuGeneradoDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idGenerado
	 */
	public long getIdGenerado() {
		return idGenerado;
	}

	/**
	 * @param idGenerado the idGenerado to set
	 */
	public void setIdGenerado(long idGenerado) {
		this.idGenerado = idGenerado;
	}

	/**
	 * @return the numeroDias
	 */
	public int getNumeroDias() {
		return numeroDias;
	}

	/**
	 * @param numeroDias the numeroDias to set
	 */
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the listaPlatos
	 */
	public List<MenuDetalleDto> getListaPlatos() {
		return listaPlatos;
	}

	/**
	 * @param listaPlatos the listaPlatos to set
	 */
	public void setListaPlatos(List<MenuDetalleDto> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}

}
