package com.fis.epo.ui.accelerator.api.jpa;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SwaggerConfig {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerConfig.class, args);
	}

	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.fis.epo.ui.accelerator.api.core.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/").useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(
								new ResponseMessageBuilder().code(500).message("500 message")
										.responseModel(new ModelRef("Error")).build(),
								new ResponseMessageBuilder().code(403).message("Forbidden!").build()))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("EPO Architecture Team", "epoarch.fis.com", "epoarch@fis.com");
		return new ApiInfoBuilder().title("Spring boot Micro REST Sample with Swagger")
				.description("A sample application to demo use of API")
				.termsOfServiceUrl("http://http://www.fisglobal.com/").contact(contact)
				.license(
						"Copyright 2015-2017 Fidelity National Information Services, Inc. and/or its subsidiaries - All Rights Reserved worldwide.")
				.licenseUrl("http://www.fisglobal.com/").version("2.0").build();
	}

}