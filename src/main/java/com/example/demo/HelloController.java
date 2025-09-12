package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HelloController {
	@PreAuthorize("hasAuthority('SCOPE_api.read')")
	@GetMapping("/api/hello")
	public Map<String,String> hello() {
	  return Map.of("message", "secure hello");
	}
    
    @GetMapping("/")
    public String root() { return "OK";}
}
