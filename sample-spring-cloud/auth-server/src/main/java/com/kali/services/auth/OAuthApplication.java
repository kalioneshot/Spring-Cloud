package com.kali.services.auth;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main class to secure your resources using OAuth2.
 * 
 * The auth-server is responsible for handling the authentication and
 * authorization process of our microservices. It connects with the User, Role,
 * and Permission from our database and issues JWT tokens containing auth-user
 * main attributes and granted authorities and persists them in the database to
 * avoid losing sessions in case our server goes down. In this example, we will
 * use MySQL...
 * 
 * @author kali
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OAuthApplication {

	static Logger logger = LoggerFactory.getLogger(OAuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(OAuthApplication.class, args);
		
		// Authorization = "CLIENT_ID:CLIENT_SECRET"
		String authorization = "adminapp:my_secret_id";
		byte[] message = authorization.getBytes(StandardCharsets.UTF_8);
		String encoded = Base64.getEncoder().encodeToString(message);
		logger.info("Authorization Base64 [CLIENT_ID:CLIENT_SECRET]: " +encoded);
		
		String password = "my_secret_id";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		logger.info("Password encoder [BCrypt]: " +encodedPassword);
	}

}
