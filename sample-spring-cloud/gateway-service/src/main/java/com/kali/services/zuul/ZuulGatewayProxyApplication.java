package com.kali.services.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.kali.services.zuul.filter.MyPreFilter;

/**
 * Spring Cloud Netflix includes an embedded Zuul proxy, which we can enable
 * with the @EnableZuulProxy annotation. This will turn the Gateway application
 * into a reverse proxy that forwards relevant calls to other services.
 * 
 * *********************** SECURITY <br>
 * 
 * 1. Zuul via the annotation @EnableOAuth2Sso going to be responsible for
 * authenticating users (with the help of an Authorization Server) and delegate
 * incoming requests to other applications. <br>
 * 2. Annotating our Zuul application with @EnableOAuth2Sso also notifies Spring
 * to configure an OAuth2TokenRelayFilter filter WebSecurityConfigurerAdaptero.
 * This filter retrieves previously obtained access tokens from users’ HTTP
 * sessions and propagates them downstream.
 * 
 * @author kali
 *
 * @EnableOAuth2Sso
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGatewayProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayProxyApplication.class, args);
	}

	/**
	 * Pre Filter
	 * 
	 * @return a {@link MyPreFilter} object.
	 */
	@Bean
	public MyPreFilter preFilter() {
		return new MyPreFilter();
	}

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

// ADD Filters
//    @Bean
//    public PostFilter postFilter() { return new PostFilter(); }
//    @Bean
//    public ErrorFilter errorFilter() { return new ErrorFilter(); }
//    @Bean
//    public RouteFilter routeFilter() { return new RouteFilter(); }
}
