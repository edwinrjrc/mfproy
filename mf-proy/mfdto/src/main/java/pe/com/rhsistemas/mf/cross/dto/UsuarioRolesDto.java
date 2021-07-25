/**
 * 
 */
package pe.com.rhsistemas.mf.cross.dto;

/**
 * @author Edwin
 *
 */
public class UsuarioRolesDto extends BaseDto {

	private static final long serialVersionUID = 6472160495627413847L;
	
	private BaseValor rol;
	private UsuarioDto usuario;
	
	/**
	 * @return the rol
	 */
	public BaseValor getRol() {
		if (rol == null) {
			rol = new BaseValor();
		}
		return rol;
	}
	/**
	 * @param rol the rol to set
	 */
	public void setRol(BaseValor rol) {
		this.rol = rol;
	}
	/**
	 * @return the usuario
	 */
	public UsuarioDto getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioDto();
		}
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
	
	
}
