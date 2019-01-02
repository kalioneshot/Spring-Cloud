package com.kali.services.auth.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * ======== <br>
 * One of our objectives is that the server must provide JSON Web Tokens.
 * ======== <br>
 * 
 * The Authorization server have to provide JWT (tokens). To provide the JWT,
 * we’ll need to create a {@link JwtTokenStore}, a
 * {@link JwtAccessTokenConverter} and a {@link DefaultTokenServices} bean, and
 * wire all that to the {@link AuthorizationServerEndpointsConfigurer}.
 * 
 * Testing : curl -X POST http://localhost:8062/oauth/token -H 'authorization:
 * Basic YWRtaW5hcHA6cGFzc3dvcmQ=' -d
 * 'grant_type=password&username=admin&password=password' | jq
 * 
 * With authorization =
 * Base64.getEncoder().encodeToString(client_id:client_secret)
 * 
 * @author kali
 *
 */
@Configuration
public class TokenStoreConfig {

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * 
	 * Use a asymmetric Key for a production environment. Note that, for the sake of
	 * simplicity, we used a symmetric key in the JwtAccessTokenConverter. This
	 * isn’t the best approach for a production environment. We should create an
	 * asymmetric key and use it to sign the converter.
	 * 
	 * You can use Keytool to produce a key pair, running the following command on
	 * the /src/main/resources/ directory: <br>
	 * keytool -genkeypair -alias mykeys -keyalg RSA -keypass mypass -keystore
	 * mykeys.jks -storepass mypass
	 * 
	 * @return a {@link JwtAccessTokenConverter] object.
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		/* Use a symmetric key for a development environment. */
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey("password");
//		return converter;
		
		/* Use a asymmetric Key for a production environment. */
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"),
				"mypass".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
		return defaultTokenServices;
	}
}
