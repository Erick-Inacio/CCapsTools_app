package com.ccapstools_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("SERVER_ADDRESS", dotenv.get("SERVER_ADDRESS"));
		System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT"));
		System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
		System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
		System.setProperty("JWT_ISSUER_URI", dotenv.get("JWT_ISSUER_URI"));
		
		SpringApplication.run(Startup.class, args);
	}

}
