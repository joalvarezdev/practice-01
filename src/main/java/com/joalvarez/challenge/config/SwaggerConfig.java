package com.joalvarez.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${app.name}")
	private String name;
	@Value("${app.description}")
	private String description;
	@Value("${app.version}")
	private String version;

	@Bean
	public Docket api() {
		return (new Docket(DocumentationType.SWAGGER_2))
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(new ApiInfo(this.name, this.description, this.version, "TermOfService",
				new Contact("joalvarez", "https://www.github.com/joalvarezdev/practice-01",
					"alvarez.joaquinez@gmail.com"), "license",
				"licenceUrl", new ArrayList<>()));
	}
}
