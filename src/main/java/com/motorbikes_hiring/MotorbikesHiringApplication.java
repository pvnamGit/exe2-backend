package com.motorbikes_hiring;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class MotorbikesHiringApplication {
	public static void main(String[] args) {
		SpringApplication.run(MotorbikesHiringApplication.class, args);
	}

	private String corsOrigin;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						// .allowedOrigins(corsOrigin)
						.allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("https://motorbike-go.herokuapp.com/");
			}
		};
	}
}
