package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    private String name;
    @NotNull
    private Double price;
    private String description;
    
    public ProductDto() {}

    public ProductDto(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
