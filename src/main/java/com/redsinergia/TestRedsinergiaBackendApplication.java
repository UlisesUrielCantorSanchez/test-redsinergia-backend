package com.redsinergia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TestRedsinergiaBackendApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(TestRedsinergiaBackendApplication.class, args);
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") 
            .allowedOrigins("http://localhost:4200")//127.0.0.1:5502
            .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")
            .allowedHeaders("*")
        	.allowCredentials(true);
    }

}
