package com.example.CodeGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CodeGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGamesApplication.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200") // Angular dev server URL
						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}
}
