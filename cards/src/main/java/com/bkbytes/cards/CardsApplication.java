package com.bkbytes.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
				info = @Info(
								title = "Cards microservice REST API Documentation",
								description = "Bk Bank Cards microservice REST API Documentation",
								version = "v1",
								contact = @Contact(
												name = "Brandon Corn",
												email = "cornbrandonk@gmail.com"
								),
								license = @License(
												name = "Apache 2.0",
												url = "https://website.com"
								)

				),
				externalDocs = @ExternalDocumentation(
								description = "BkBank Cards microservice REST API Documentation",
								url = "https://dummy-url.com"
				)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
