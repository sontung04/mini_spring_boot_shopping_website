package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/auth/search")
    public Optional<User> search() {
        return repo.findById(1L);
    }
    
    @GetMapping("/auth/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello, world!");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> securedHello(){
        return ResponseEntity.ok("Hello, secured world!");
    }
}
