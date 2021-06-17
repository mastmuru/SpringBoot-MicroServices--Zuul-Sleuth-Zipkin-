package com.fa.microsvc;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

	@Bean
	public Docket motorAPI() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Zuul API Gateway").select()
				.apis(RequestHandlerSelectors.any()).paths(motorAPIPaths()).build().apiInfo(apiInfo()).enable(true)
				.forCodeGeneration(false);
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(true).defaultModelsExpandDepth(-1)
				.defaultModelExpandDepth(-1).defaultModelRendering(ModelRendering.MODEL).displayRequestDuration(false)
				.docExpansion(DocExpansion.FULL).filter(false).operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(true).tagsSorter(TagsSorter.ALPHA).validatorUrl(null).build();
	}

	private Predicate<String> motorAPIPaths() {
		return Predicates.or(regex("/proxy.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Workflow MicroServices", "Description", "V1.0.1", "Terms Of Conditions",
				new Contact("FA Softwares Services Pvt. Ltd.", "", "info@fasoftwares.com"), "license", "",
				new ArrayList<>());
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
				registry.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/");
			}
		};
	}

}