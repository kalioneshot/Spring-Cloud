package com.kali.services.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Then enable running embedded server configuration during application boot
 * use @EnableConfigServer annotation.
 * 
 * By default Spring Cloud Config Server store the configuration data inside Git
 * repository. This is very good choice in production mode, but for the sample
 * purpose file system backend will be enough. It is really easy to start with
 * config server, because we can place all the properties in the classpath.
 * Spring Cloud Config by default search for property sources inside the
 * following locations: classpath:/, classpath:/config, file:./, file:./config.
 * 
 * We place all the property sources inside src/main/resources/config. The YAML
 * filename will be the same as the name of service. For example, YAML file for
 * discovery-service will be located here:
 * src/main/resources/config/discovery-service.yml.
 * 
 * And last two important things. If you would like to start config server with
 * file system backend you have activate Spring Boot profile native. It may be
 * achieved by setting parameter --spring.profiles.active=native during
 * application boot. I have also changed the default config server port (8888)
 * to 8061 by setting property server.port in bootstrap.yml file.
 * 
 * @author kali
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigApplication.class).run(args);
	}

}
