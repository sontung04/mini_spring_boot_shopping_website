package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {  

    private Long id;
    private Long userId;

    private List<OrderProductDto> orderProducts;

    private Double total;

    public OrderDto() {}

    public OrderDto(Long id, Long userId, List<OrderProductDto> orderProducts, Double total) {
        this.id = id;
        this.userId = userId;
        this.orderProducts = new ArrayList<>(orderProducts);
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderProductDto> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProduct(List<OrderProductDto> orderProducts) {
        this.orderProducts = new ArrayList<>(orderProducts);
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }
}
