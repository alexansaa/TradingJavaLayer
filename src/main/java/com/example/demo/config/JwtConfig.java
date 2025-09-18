package com.example.demo.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtValidators;

@Configuration
public class JwtConfig {
	private String issuer = System.getenv("JWT_ISSUER_URI");
	private static String tradingAud = System.getenv("ACCEPTED_AUD");
	

	  // Accept either the Application ID URI or the API client ID as aud
	  private static final List<String> ACCEPTED_AUD = List.of(
			  tradingAud
	  );

	  @Bean
	  JwtDecoder jwtDecoder() {
	    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuer);
	    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
	    OAuth2TokenValidator<Jwt> audienceValidator = token -> {
	    	System.out.println(token.getTokenValue());
	      for (String aud : token.getAudience()) {
	    	  System.out.println(String.format("audience: %s", aud));
	        if (ACCEPTED_AUD.contains(aud)) return OAuth2TokenValidatorResult.success();
	      }
	      return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token",
	          "Required audience is missing", null));
	    };
	    decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator));
	    return decoder;
	  }
}
