package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Configuration
public class ProductConfig {

    private final ProductRepository productRepository;

    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    CommandLineRunner seeder(){
        return _ -> {
            productRepository.save(new Product("Computer", 10000.0, ""));
            productRepository.save(new Product("Mouse", 100.0, ""));
            productRepository.save(new Product("Keyboard", 200.0, ""));
            productRepository.save(new Product("Book", 10.0, "Nghin le mot dem"));
        };
    }
}
