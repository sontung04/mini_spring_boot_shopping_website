package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private static ProductDto toDto(Product product) {
        return new ProductDto(
            product.getName(),
            product.getPrice(),
            product.getDescription()
        );
    }
    
    public ProductDto createNewProduct(ProductDto productDto) {
        var product = new Product(
            productDto.getName(),
            productDto.getPrice(),
            productDto.getDescription()
        );
        repository.save(product);
        return productDto;
    }

    public List<ProductDto> findAll() {
        return repository.findAll()
                        .stream()
                        .map(product -> toDto(product))
                        .toList();
    }

    public ProductDto findById(Long id) {
        var product = repository.findById(id)
                                .orElseThrow();
        return toDto(product);
    }

    public List<ProductDto> findByName(String name) {
        return repository.findByName(name)
                        .stream()
                        .map(product ->toDto(product))
                        .toList();
    }

    public ProductDto updateProduct(Long id, ProductDto updatedProductInfo) {
        var product = repository.findById(id)
                                .orElseThrow();
        product.setName(updatedProductInfo.getName());
        product.setPrice(updatedProductInfo.getPrice());
        product.setDescription(updatedProductInfo.getDescription());
        var savedProduct = repository.save(product);
        return toDto(savedProduct);
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id:" + id);
        }
        repository.deleteById(id);
    }
}
