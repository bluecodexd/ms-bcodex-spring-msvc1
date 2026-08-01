package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello Spring Boot!",
                "method", "GET"
        );
    }

    @PostMapping("/hello")
    public Map<String, Object> create(@RequestBody Map<String, Object> body) {
        return Map.of(
                "message", "POST OK",
                "received", body
        );
    }
}