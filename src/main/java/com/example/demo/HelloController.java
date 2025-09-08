package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello, world!");
    }
    
    @GetMapping("/")
    public String root() { return "OK";}
}
