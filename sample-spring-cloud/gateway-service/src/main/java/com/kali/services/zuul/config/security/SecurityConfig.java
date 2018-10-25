package com.kali.services.zuul.config.security;

/**
 * Annotating our APIGateway application {ZuulGatewayProxyApplication}
 * with @EnableOAuth2Sso also notifies Spring to configure an
 * OAuth2TokenRelayFilter filter. <br>
 * 
 * The OAuth2TokenRelayFilter Filter is carried by the
 * WebSecurityConfigurerAdapter. Note that we’re also using the @Order
 * annotation in our AppConfiguration configuration class. This is to make sure
 * that Filters created by our WebSecurityConfigurerAdapter take precedence over
 * Filters created by other WebSecurityConfigurerAdapters.<br>
 * 
 * We are using Thymeleaf for our view template engine and Thymeleaf - Spring
 * Security integration modules in order to utilize the sec:authentication and
 * sec:authorize attributes. That library has some other useful features. It
 * will automatically add the CSRF token to our login form and helps us resolve
 * templates from /src/main/resource/templates directory. Surprisingly, that is
 * not all - we also have to provide security configuration to allow the login
 * page to display and disable basic security. In addition, we need to change
 * default in-memory based authentication to JDBC-based. In this sample, we use
 * a MySQL database.
 * 
 * @author kali
 *
 */
//@Configuration
//@EnableWebSecurity
//@Order(-10)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {
//	@Autowired
//	private DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
//				.httpBasic().disable();
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource);
////		auth.inMemoryAuthentication().withUser("root").password("password").roles("USER");
//	}

}
