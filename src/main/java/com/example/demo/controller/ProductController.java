package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> all() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id);
    }
    
    @GetMapping("/title/{name}")
    public List<Product> findByName(@PathVariable String name){
        return productRepository.findByName(name);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id){
        if (product.getID() != id){
            throw new NoSuchElementException();
        }
        productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return productRepository.save(product);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        productRepository.deleteById(id);
    }

    @Bean
    CommandLineRunner seeder(){
        return _ -> {
            productRepository.save(new Product("Computer", 10000.0));
            productRepository.save(new Product("Mouse", 100.0));
            productRepository.save(new Product("Keyboard", 200.0));
            productRepository.save(new Product("Book", 10.0, "Nghin le mot dem"));
        };
    }
}
