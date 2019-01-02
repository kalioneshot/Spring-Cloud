package com.kali.services.auth.config.oauth;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 
 * We’ve already got our token provider (OAuth2AuthorizationServerConfiguration)
 * and now it is time to use it. Enable Oauth2 authentication on Spring is
 * pretty straight forward, all you need to do is annotate a configuration class
 * with @EnableResourceServer. The annotation enables a Spring Security filter
 * that authenticates requests via an incoming OAuth2 token. To customize the
 * security setting we’ll need to provide a ResourceServerConfigurerAdapter
 * bean.
 * 
 * 
 * Observe that we had wired the TokenStore and the DefaultTokenServices, beans
 * that were defined back on the AuthorizationServerConfigurerAdapter. Both
 * adapters must share the same logic on how to create and extract the token in
 * order for the authentication process to work.
 * 
 * //////// Method Security \\\\\\\\<br>
 * <br>
 * However, it is possible to add other layers of security, directly protecting
 * our Services, Beans on even Methods. Spring’s Method Security is a highly
 * sophisticated security system, that allows a more granular control over our
 * system. To activate it just annotate a configuration class
 * with @EnableGlobalMethodSecurity<br>
 * If you include prePostEnable=true on the annotation, it will be possible to
 * use the expression-based syntax, the Spring EL, allowing Boolean logic to be
 * encapsulated on a single expression :<br>
 * - hasRole([role]): Returns true if the current principal has the specified
 * role.<br>
 * - hasAnyRole([role1,role2]): Returns true if the current principal has any of
 * the supplied roles.<br>
 * - hasAuthority([authority]): Returns true if the current principal has the
 * specified authority.<br>
 * - hasAnyAuthority([authority1,authority2]): Returns true if the current
 * principal has any of the supplied roles.<br>
 * - isAuthenticated(): Returns true if the user is not anonymous.<br>
 * - hasPermission(Object target, Object permission): Returns true if the user
 * has access to the provided target for the given permission.<br>
 * - The denyAll and permitAll are self-explanatory.<br>
 * 
 * Once the Method Security is enabled you can start to secure your code. All
 * you need to do is annotate your Methods, Class or Beans with @PreAuthorize
 * followed by the proper expression.
 * 
 * See
 * : @PreAuthorize("permitAll()"), @PreAuthorize("hasRole('USER')"), @PreAuthorize("permitAll()"), @PreAuthorize("isAuthenticated()")
 * 
 * @author kali
 *
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	// The DefaultTokenServices bean provided at the AuthorizationConfig
	@Autowired
	private DefaultTokenServices tokenServices;

	// The TokenStore bean provided at the AuthorizationConfig
	@Autowired
	private TokenStore tokenStore;

	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	// To allow the ResourceServerConfigurerAdapter to understand the token,
	// it must share the same characteristics with
	// AuthorizationServerConfigurerAdapter.
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		// -- If the token is not the same an error shown.
		// {
		// "error": "access_denied",
		// "error_description": "Invalid token does not contain resource id
		// }
		resources.resourceId(resourceId).tokenServices(tokenServices).tokenStore(tokenStore);
	}

	// The resource server has the authority to define the permission for any
	// endpoint.
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// -- define URL patterns to enable OAuth2 security
		http//
				.csrf().disable().anonymous().disable().authorizeRequests()//
				// when restricting access to 'Roles' you must remove the "ROLE_" part role
				// for "ROLE_USER" use only "USER"
				.antMatchers("/oauth/token").permitAll()
				// .antMatchers("/h2-console/**").permitAll()

				.antMatchers("/hello/**").access("hasRole('ADMIN')")
				.antMatchers("/api/**").authenticated().and().exceptionHandling()//
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))//
				.accessDeniedHandler(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))//
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
		// Restriction / path.

	}
}
