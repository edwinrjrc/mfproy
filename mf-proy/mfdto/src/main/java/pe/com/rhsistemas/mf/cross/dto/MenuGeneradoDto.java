/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Edwin
 *
 */
public class MenuGeneradoDto extends BaseDto {
	
	private static final long serialVersionUID = 1440805495819436622L;
	
	private long idGenerado;
	private Date fechaGenerado;
	private int numeroDias;
	private int idPersona;
	private List<MenuDetalleDto> listaPlatos;
	private Date fechaDesde;
	private Date fechaHasta;

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

	/**
	 * @return the fechaGenerado
	 */
	public Date getFechaGenerado() {
		return fechaGenerado;
	}

	/**
	 * @param fechaGenerado the fechaGenerado to set
	 */
	public void setFechaGenerado(Date fechaGenerado) {
		this.fechaGenerado = fechaGenerado;
	}

	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
