package pe.com.rhsistemas.mf.auth.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 8482779051037265768L;
	
	private String token;
    private final UserDetails principle;

    public TokenBasedAuthentication(String token, UserDetails principle ) {
        super( principle.getAuthorities() );
        this.token = token;
        this.principle = principle;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return principle;
    }

}
