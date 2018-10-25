package com.kali.services.zuul.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Microservices API Documentation with Swagger2 configuration.
 * 
 * First, we should configure Swagger on every microservice. To enable it we
 * have to declare @EnableSwagger2 on the main class. <br>
 * 
 * API documentation will be automatically generated from source code by Swagger
 * library during application startup. The process is controlled by Docket @Bean
 * which is also declared in the main class. <br>
 * 
 * API version is read from pom.xml file using MavenXpp3Reader. We also set some
 * other properties like title, author and description using apiInfo method. By
 * default, Swagger generates documentation for all REST services including
 * those created by Spring Boot.<br>
 * 
 * API documentation is available under
 * http://{zuulServer}:{zuulPort}/swagger-ui.html.
 * http://localhost:8060/swagger-ui.html
 * 
 * @author kali
 *
 */
@Configuration
@EnableSwagger2
public class DocumentationApiConfig {

	/**
	 * The Zuul properties. .
	 */
	@Autowired
	private ZuulProperties properties;

	/**
	 * Get routes dynamically from the ZuulProperties.
	 * 
	 * @return a {@link SwaggerResourcesProvider} object.
	 */
	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider() {
		return () -> {
			List<SwaggerResource> resources = new ArrayList<>();
			properties.getRoutes().values().stream().forEach(
					route -> resources.add(createResource(properties.getPrefix(), route.getServiceId(), "2.0")));
			return resources;
		};
	}

	/**
	 * This method to create the swagger resource.
	 * 
	 * @param prefix    : The prefix API.
	 * @param serviceId : The id service.
	 * @param version   : The swagger version.
	 * @return a {@link SwaggerResource} object.
	 */
	private SwaggerResource createResource(String prefix, String serviceId, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(serviceId);
		swaggerResource.setLocation(prefix + "/" + serviceId + "/v2/api-docs");
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

	/**
	 * UI configuration.
	 * 
	 * @return a {@link UiConfiguration} object.
	 */
	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().validatorUrl("").docExpansion(DocExpansion.LIST)
				.defaultModelRendering(ModelRendering.MODEL)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).displayRequestDuration(true)
				.build();
	}
}
