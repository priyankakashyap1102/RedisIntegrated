package com.medicine.main.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;


@Configuration
@EnableSwagger2
public class SwaggerConfig {


	@Bean
	public Docket pocAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.apiInfo(metaInfo())
				.select()
				.paths(path())
				.build();
	}

		Predicate<String> path() {
			return or(regex("/medicine/.*"),regex("/login"));
		}
	 
	 private ApiInfo metaInfo() {

			ApiInfo apiInf = new ApiInfo("Medicine Management", "Spring Boot - Proof of Concept", "1.0",
					"https://spring.io/projects/spring-boot",
	    		    new Contact("Priyanka Kashyap", "https://github.com/priyankakashyap1102/RedisIntegrated.git", "priyanka.kashyap@infogain.com"), 
	    		    "Infogain Trainee License 1.0", "https://www.infogain.com");
			return apiInf;

	    }
}
