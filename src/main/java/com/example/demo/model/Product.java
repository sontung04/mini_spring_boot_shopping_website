package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String name;
    @NotNull
    private Double price;
    private String description;

    public Product(){}

    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', price=%f, description=%s]", id, name, price, description);
    }
    
    public Long getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }
}
