package com.kali.services.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Then you should enable running embedded discovery server during application
 * boot by setting @EnableEurekaServer annotation on the main class.
 * 
 * Once you have succesfully started application you may visit Eureka Dashboard
 * available under address http://localhost:8061/.
 * 
 * @author kali
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryApplication.class).run(args);
	}

}
