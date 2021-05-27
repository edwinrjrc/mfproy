package pe.com.rhsistemas.mf.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import pe.com.rhsistemas.mf.auth.security.TokenHelper;
import pe.com.rhsistemas.mf.auth.security.auth.RestAuthenticationEntryPoint;
import pe.com.rhsistemas.mf.auth.security.auth.TokenAuthenticationFilter;
import pe.com.rhsistemas.mf.auth.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    TokenHelper tokenHelper;
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( jwtUserDetailsService )
            .passwordEncoder( passwordEncoder() );
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
            .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            //.antMatchers(HttpMethod.POST,"/users").hasAnyRole("USER", "ADMIN")
            //.anyRequest().authenticated()
            .and()
            .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) {
        // TokenAuthenticationFilter will ignore the below paths
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/auth/login"
        );
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	String queryUsuario = "select tx_login,tx_password,(in_estado='A') as in_estado from sistema.usuarios where tx_login = ?";
    	String query2 = "select t2.tx_login, t3.nombre "
    			      + "  from sistema.usuarios_roles t1, sistema.usuarios t2, sistema.roles t3"
    			      + " where t1.id_persona = t2.id_persona"
    			      + "   and t1.id_rol = t3.id_rol"
    			      + "   and t2.tx_login = ?";
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcAuth = auth.jdbcAuthentication().dataSource(dataSource);
		jdbcAuth.usersByUsernameQuery(queryUsuario).authoritiesByUsernameQuery(query2);
		
    }
}
