package pe.com.rhsistemas.mf.auth.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.entity.UsuariosRole;

public class SecurityUser extends User {

	private static final long serialVersionUID = 7371606535519304475L;

	public SecurityUser(Usuario user) {
        super(user.getTxLogin(),
              user.getTxPassword(),valores(user));
    }
	
	private static List<SimpleGrantedAuthority> valores(Usuario user){
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		SimpleGrantedAuthority bean = null;
		for (UsuariosRole usuarioRole : user.getUsuariosRoles()) {
			bean = new SimpleGrantedAuthority(usuarioRole.getRole().getNombre());
			roles.add(bean);
		}
		
		return roles;
	}

}
