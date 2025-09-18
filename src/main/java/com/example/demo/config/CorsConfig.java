package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	      // allow your Azure Static Web Apps origin (wildcard pattern OK)
	      .allowedOriginPatterns("https://*.azurestaticapps.net", "http://localhost:*")
	      .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	      .allowedHeaders("*")
	      .allowCredentials(true)   // set true only if you really use cookies/Authorization with credentials
	      .maxAge(3600);
	  }

  /* @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration cfg = new CorsConfiguration();
    cfg.setAllowedOrigins(List.of(
      "https://brave-meadow-0b7a0fa1e.1.azurestaticapps.net",
      "http://localhost:3000",
      "http://localhost:8888" // SPA via Docker
    ));
    cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
    cfg.setAllowedHeaders(List.of("Authorization","Content-Type"));
    cfg.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", cfg);
    return source;
  } */
}
