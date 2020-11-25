package com.gatech.cs6440.drugabuse;

import com.gatech.cs6440.drugabuse.engine.FhirEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class DrugabuseApplication implements ApplicationRunner {

	@Autowired
	FhirEngine fhirEngine;


	// API Config
	private static final String AUTHOR_NAME = "Prashant Srivastava";
	private static final String AUTHOR_EMAIL = "psrivastava48@gatech.edu";
	private static final String GITHUB_SOURCE = "https://github.gatech.edu/psrivastava48/cs6440_ip";
	private static final String BASE_PACKAGE = "com.gatech.cs6440.drugabuse";
	private static final String API_TITLE ="Drug Abuse API";
	private static final String API_DESCRIPTION="API to identify and report drugabuse patients";
	private static final String API_VERSION = "1.0";

	// Main Spring Boot Application
	public static void main(String[] args) {
		SpringApplication.run(DrugabuseApplication.class, args);
	}


	// Fetch Details from FHIR upon Startup
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Fetch details and save it to the database.
		fhirEngine.fetchUserDetails();
	}


	// API Documentation
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				API_TITLE,
				API_DESCRIPTION,
				API_VERSION,
				"Free to use",
				new springfox.documentation.service.Contact(AUTHOR_NAME, GITHUB_SOURCE, AUTHOR_EMAIL ),
				"API License",
				GITHUB_SOURCE,
				Collections.emptyList());
	}
}
