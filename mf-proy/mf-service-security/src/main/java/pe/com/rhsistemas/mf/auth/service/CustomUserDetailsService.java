package pe.com.rhsistemas.mf.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.repo.UserRepository;
import pe.com.rhsistemas.mf.auth.security.SecurityUser;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario user = userRepository.findByTxLogin(username).get(0);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return (UserDetails) new SecurityUser(user);
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
    	Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        //log.debug("Re-authenticating user '"+ username + "' for password change request.");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));

        //log.debug("Changing password for user '"+ username + "'");

        UserDetails userDetails = (UserDetails) loadUserByUsername(username);
        Usuario usuario = new Usuario();
        usuario.setTxLogin(userDetails.getUsername());
        usuario.setTxPassword(passwordEncoder.encode(newPassword));
        userRepository.save(usuario);
    }
    
    public String encodePassword(String password) {
    	String pass = "";
    	pass = passwordEncoder.encode(password);
    	return pass;
    }
}
