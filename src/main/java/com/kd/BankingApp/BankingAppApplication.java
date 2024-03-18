package com.kd.BankingApp;

import com.kd.BankingApp.config.SecretKeyGenerator;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kd.BankingApp.repository")
@OpenAPIDefinition(
		info = @Info(
				title = "KD Banking App",
				description = "Backend Rest APIs for KD Bank",
				version = "v.1.0" ,
				contact = @Contact(
						name = "Kd Batmunkh",
						email = "khishigdavaa.b1006@gmail.com",
						url = "https://github.com/Kd1006/BankingApp"
				),
				license = @License(
						name = "Kd Java App Development",
						url = "https://github.com/Kd1006/BankingApp"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Banking App Documentation",
				url = "https://github.com/Kd1006/BankingApp"
		)

)


public class BankingAppApplication {
	@Autowired
	SecretKeyGenerator secretKeyGenerator;
	private static final Logger logger = LoggerFactory.getLogger(BankingAppApplication.class);

	public static void main(String[] args) {
		String token = SecretKeyGenerator.generateSecretKey();
		System.out.println("Secret key " + token);

		SpringApplication.run(BankingAppApplication.class, args);
		logger.info("Application started successfully!");
	}

}
//http://localhost:8080/swagger-ui/index.html
