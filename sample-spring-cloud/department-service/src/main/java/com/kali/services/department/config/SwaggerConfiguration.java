package com.kali.services.department.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration.
 * 
 * @author kali
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	/**
	 * Swagger Springfox configuration.
	 * 
	 * @return a {@link Docket} object.
	 */
	@Bean
	public Docket swaggerSpringfoxDocket() {
		// @formatter:off
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.kali.services.department.api")).paths(PathSelectors.any())
				.build().pathMapping("/").directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false)
				.enableUrlTemplating(true).forCodeGeneration(true).apiInfo(apiInfo());
		// @formatter:on
		return docket;
	}

	/**
	 * Get information about the employee's service.
	 * 
	 * @return a {@link ApiInfo} object.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Department API")
				.description("This service allow to get informations about department.").termsOfServiceUrl("#")
				.contact(new Contact("Kali", "#", "cedric.roignant@gmail.com")).license("Licence").licenseUrl("#")
				// Version : MavenXpp3Reader reader = new MavenXpp3Reader()
				.version("1.0").build();
	}
}
