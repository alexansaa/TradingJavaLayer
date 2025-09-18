package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.JwtValidators;

import jakarta.annotation.PostConstruct;

@Configuration
public class JwtConfig {
	//private String issuer = System.getenv("JWT_ISSUER_URI");
	//private static String tradingAud = System.getenv("ACCEPTED_AUD");
	
	@Value("${accepted.aud:}")
	private String acceptedAudRaw;
	
	private List<String> acceptedAud;

	  // Accept either the Application ID URI or the API client ID as aud
	  //private static final List<String> ACCEPTED_AUD = List.of(
		//	  tradingAud
	  //);
	
	@PostConstruct
	  void init() {
	    // split, trim, and filter empties
	    acceptedAud = Arrays.stream(acceptedAudRaw.split(","))
	        .map(String::trim)
	        .filter(s -> !s.isEmpty())
	        .toList();

	    if (acceptedAud.isEmpty()) {
	      throw new IllegalStateException(
	          "accepted.aud / ACCEPTED_AUD is not set. Provide at least one audience.");
	    }
	  }

	  public List<String> getAcceptedAud() {
	    return acceptedAud;
	  }
	  
	  @Value("${jwt.issuer.uri:}")
	  private String issuer;

	  @Bean
	  JwtDecoder jwtDecoder() {
	    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuer);
	    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
	    OAuth2TokenValidator<Jwt> audienceValidator = token -> {
	    	System.out.println(token.getTokenValue());
	      for (String aud : token.getAudience()) {
	    	  System.out.println(String.format("audience: %s", aud));
	        if (getAcceptedAud().contains(aud)) return OAuth2TokenValidatorResult.success();
	      }
	      return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token",
	          "Required audience is missing", null));
	    };
	    decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator));
	    return decoder;
	  }
}
