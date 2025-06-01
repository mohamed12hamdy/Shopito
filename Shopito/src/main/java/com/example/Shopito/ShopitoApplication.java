package com.example.Shopito;

import com.example.Shopito.Entities.users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopitoApplication.class, args);
	}



}
