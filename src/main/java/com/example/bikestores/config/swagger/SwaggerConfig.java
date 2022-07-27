package com.example.bikestores.config.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact CONTACT = new Contact("Luca Nocella", null, null);
	public static final ApiInfo DEFAULT_API = new ApiInfo("Bikestores API", "Bikestores API reference for developers", "1.0", null, CONTACT,
			null, null, new ArrayList<>());
	protected static final Set<String> consumes = new HashSet<>(Arrays.asList("application/json"));
	protected static final Set<String> produces = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API)
				.consumes(consumes).produces(produces)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build();
	}

}