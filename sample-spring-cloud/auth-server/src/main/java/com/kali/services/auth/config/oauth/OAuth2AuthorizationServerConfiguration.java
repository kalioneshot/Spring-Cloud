package com.kali.services.auth.config.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * The global OAuth2.0 Server Configuration.
 * 
 * Be careful : Nonetheless, you must pay special attention to the revocation
 * process of the JWT, considering that it won’t be saved in a database.
 * 
 * @author kali
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Configure the ClientDetailsService, declaring individual clients and their
	 * properties.
	 */
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

	/**
	 * Configure the non-security features of the Authorization Server endpoints,
	 * like token store, token customizations, user approvals and grant types.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenServices(tokenServices).tokenStore(tokenStore)
				.accessTokenConverter(accessTokenConverter).userDetailsService(userDetailsService);
	}

	/**
	 * Configure the security of the Authorization Server, which means in practical
	 * terms the /oauth/token endpoint.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		oauthServer
//				// we're allowing access to the token only for clients with
//				// 'ROLE_TRUSTED_CLIENT' authority
//				.tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
//				.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
	}

}
