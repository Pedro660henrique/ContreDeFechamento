package com.aro.FechamentoAro.Security.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Profile("dev")
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
	 private static final String[] ALLOWED_ORIGINS = {
		        "https://FechamentoAro.com", 
		        "https://app.FechamentoAro.com",
		        "http://localhost:8080",
		        "http://127.0.0.1:8080"
		    };

		    @Override
		    public void addCorsMappings(CorsRegistry registry) {
		        registry.addMapping("/**")
		            .allowedOrigins(ALLOWED_ORIGINS)
		            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
		            .allowedHeaders("*")
		            .exposedHeaders("Authorization")
		            .allowCredentials(true)
		            .maxAge(3600);
		    }
}