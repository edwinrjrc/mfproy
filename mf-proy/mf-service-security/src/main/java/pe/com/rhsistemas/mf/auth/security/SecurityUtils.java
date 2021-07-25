package pe.com.rhsistemas.mf.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import pe.com.rhsistemas.mf.auth.entity.Usuario;
import pe.com.rhsistemas.mf.auth.repo.UserRepository;

@Component
public class SecurityUtils {

    private UserRepository userRepository;

    @Autowired
    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario getLoginUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
        {
        	Usuario user = (Usuario) authentication.getPrincipal();
            return userRepository.findByTxLogin(user.getTxLogin()).get(0);
        }
        return null;
    }
}
