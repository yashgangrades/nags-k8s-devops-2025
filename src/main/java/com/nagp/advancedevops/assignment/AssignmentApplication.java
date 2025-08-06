package com.nagp.advancedevops.assignment;

import com.nagp.advancedevops.assignment.dto.UserRecordInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {UserRecordInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "UserRecord microservice REST API Documentation",
				description = "NAGP 2025 UserRecord microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Yash Gangrade",
						email = "yash.gangrade@nagarro.com",
						url = "https://www.nagarro.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "UserRecord microservice REST API Documentation",
				url = "https://your-docs-url/swagger-ui.html"
		)
)
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
