/**
 * 
 */
package pe.com.rhsistemas.mfsermenu.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;

/**
 * @author Edwin
 *
 */
@Component
public class AutorizacionFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AutorizacionFilter.class);
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.header:Authorization}")
	private String authHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(authHeader);
		log.debug("header ::"+header);
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(authHeader);
		if (token != null) {
			try {
				// Se procesa el token y se recupera el usuario.
				String user = Jwts.parser()
							.setSigningKey(secret)
							.parseClaimsJws(token.replace("Bearer ", ""))
							.getBody()
							.getSubject();

				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			} catch (Exception e) {
				log.error("Token JWT invalido o con firma incorrecta", e);
			}
			return null;
		}
		return null;
	}

}
