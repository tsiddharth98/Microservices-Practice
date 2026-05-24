package com.shunya.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*@ComponentScans({ @ComponentScan("com.eazybytes.cards.controller") })
@EnableJpaRepositories("com.eazybytes.cards.repository")
@EntityScan("com.eazybytes.cards.model")*/
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "ApexBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Siddhartha Tiwari",
						email = "siddhartht@gmail.com",
						url = "www.siddharthtiwari.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://localhost:8090/swagger-ui/index.html"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "ApexBank Cards microservice REST API Documentation",
				url = "http://localhost:8090/swagger-ui/index.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
