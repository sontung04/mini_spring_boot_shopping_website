package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.service.OrderService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Order {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_product_id")
    private List<OrderProduct> orderProducts;

    private Double total;

    public Order() {}

    public Order(User user, List<OrderProduct> orderProducts) {
        this.user = user;
        this.orderProducts = new ArrayList<>(orderProducts);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProduct(List<OrderProduct> orderProducts) {
        this.orderProducts = new ArrayList<>(orderProducts);
    }

    public Double getTotal() {
        setTotal();
        return total;
    }

    public void setTotal() {
        OrderService.calcTotal(this);
    }
}
