package com.kd.BankingApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kd.BankingApp.repository")
public class BankingAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(BankingAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
		logger.info("Application started successfully!");
	}
}
