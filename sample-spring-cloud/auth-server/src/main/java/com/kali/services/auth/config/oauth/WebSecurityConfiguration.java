package com.kali.services.auth.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Provides a base class for creating a SecurityConfig.
 * 
 * {@link @EnableWebSecurity} : Enables spring security and hints Spring Boot to
 * apply all the sensitive defaults.
 * 
 * We also need to edit some things in the SecurityConfig class. First of all,
 * its filtering order must be changed to a lower one, allowing that the
 * configurations defined on ResourceConfig class take precedence over it.
 * 
 * @author kali
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Change the HttpSecurity configuration defined in the SecurityConfig. Let’s
	 * add an httpBasic authentication and restrict all access only to authenticated
	 * users.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http//
				.csrf().disable()
				// Make sure we use stateless session; session won't be used to store user's
				// state.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and()// any other requests must be authenticated
				.authorizeRequests().anyRequest().authenticated().and().anonymous().disable();

	}

	/**
	 * Since we’re saving our user, let’s add a password encryptor.
	 * 
	 * @return {@link BCryptPasswordEncoder} object.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * The place to configure the default authenticationManager @Bean.
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
