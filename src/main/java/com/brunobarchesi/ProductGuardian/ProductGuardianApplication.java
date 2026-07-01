package com.brunobarchesi.ProductGuardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductGuardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductGuardianApplication.class, args);
	}

}
