package com.xenecca.api.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket actuatorApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.xenecca.api.controller")).paths(PathSelectors.any())
				.build().securityContexts(Arrays.asList(actuatorSecurityContext()))
				.securitySchemes(Arrays.asList(basicAuthScheme()));
	}

	@SuppressWarnings("deprecation")
	private SecurityContext actuatorSecurityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference()))
				.forPaths(PathSelectors.any()).build();
	}

	private SecurityScheme basicAuthScheme() {
		return new BasicAuth("basicAuth");
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", new AuthorizationScope[0]);
	}
}
