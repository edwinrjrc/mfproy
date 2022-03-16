/**
 * 
 */
package pe.com.rhsistemas.mfserviceingrediente.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import pe.com.rhsistemas.mfserviceingrediente.security.AutorizacionFilter;


/**
 * @author Edwin
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param disableDefaults
	 */
	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().and()
				.authorizeRequests().antMatchers("/mf-service-plato/**").permitAll()
				.and().addFilterBefore(new AutorizacionFilter(), BasicAuthenticationFilter.class);

		http.csrf().disable();
	}
}
