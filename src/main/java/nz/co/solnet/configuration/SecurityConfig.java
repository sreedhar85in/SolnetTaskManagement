package nz.co.solnet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/**");
		web.ignoring().antMatchers("/actuator/**", "/imgs/**", "/css/**", "/public/**", "/swagger-ui.html",
				"/js/swagger-oauth.js", "/images/pacman_logo.svg", "/js/swagger.js", "/js/swagger-ui.js",
				"/images/favicon-32x32.png", "/images/favicon-16x16.png", "/images/favicon.ico",
				"/swagger-resources/**", "/v2/api-docs/**", "/webjars/**", "/v1/api/**", "/client-auth/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/swagger-resources/**").permitAll().antMatchers(HttpMethod.GET, "/vehicles/**")
				.permitAll().antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll().anyRequest().permitAll();
		// @formatter:on
	}

}