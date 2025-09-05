package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderProductDto;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }
    
    public static Double calcTotal(Order order) {
        Double total = 0.0;
        List<OrderProduct> orderProducts = order.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.getTotalPrice();
        }
        return total;
    }

    private static OrderProductDto toDto(OrderProduct orderProduct) {
        return new OrderProductDto(
            orderProduct.getProduct().getId(),
            orderProduct.getQuantity()
        );
    }

    private static OrderDto toDto(Order order) {
        return new OrderDto(
            order.getId(),
            order.getUser().getId(),
            order.getOrderProducts()
                        .stream()
                        .map(orderProduct -> toDto(orderProduct))
                        .toList(),
            order.getTotal()
        );  
    }

    public List<OrderDto> all() {
        return orderRepository.findAll()
                            .stream()
                            .map(order -> toDto(order))
                            .toList();
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                                    .orElseThrow();
        return toDto(order);
    }

    public List<OrderDto> findByUserId(Long id) {
        return orderRepository.findByUserId(id)
                            .stream()
                            .map(order -> toDto(order))
                            .toList();
    }

    public OrderDto createNewOrder(List<OrderProductDto> productList) {
        Order order = new Order();
        // Find user by authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        order.setUser(userService.findUserByAuthentication(authentication));

        List<OrderProduct> orderProducts = new ArrayList<>();

        // Convert List<OrderProductDto> into List<OrderProduct>
        for (OrderProductDto orderProductDto : productList) {
            OrderProduct orderProduct = new OrderProduct();
            Long productId = orderProductDto.getProductId();
            Product product = productRepository.findById(productId)
                                                .orElseThrow();
            orderProduct.setProduct(product);
            orderProduct.setQuantity(orderProductDto.getQuantity());
            orderProduct.setTotalPrice();
            orderProducts.add(orderProduct);
        }

        order.setOrderProduct(orderProducts);
        order.setTotal();
        Order savedOrder = orderRepository.save(order);
        return toDto(savedOrder);
    }

    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with Id = " + id);
        }
        orderRepository.deleteById(id);
    }
}
