/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class RolDto extends BaseDto {

	private static final long serialVersionUID = 3462919949169680187L;
	
	Integer idrol;
	String nombre;
	
	public RolDto() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the idrol
	 */
	public Integer getIdrol() {
		return idrol;
	}
	/**
	 * @param idrol the idrol to set
	 */
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
