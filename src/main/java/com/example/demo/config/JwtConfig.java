package com.example.demo.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtValidators;

@Configuration
public class JwtConfig {
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	  private String issuer;

	  // Accept either the Application ID URI or the API client ID as aud
	  private static final List<String> ACCEPTED_AUD = List.of(
	      "api://03d3f3b8-6d74-49ab-bbe8-981cd5464328"
	  );

	  @Bean
	  JwtDecoder jwtDecoder() {
	    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuer);
	    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
	    OAuth2TokenValidator<Jwt> audienceValidator = token -> {
	      for (String aud : token.getAudience()) {
	        if (ACCEPTED_AUD.contains(aud)) return OAuth2TokenValidatorResult.success();
	      }
	      return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token",
	          "Required audience is missing", null));
	    };
	    decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator));
	    return decoder;
	  }
}
