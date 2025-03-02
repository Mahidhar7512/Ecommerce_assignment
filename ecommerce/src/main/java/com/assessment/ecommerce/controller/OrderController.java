package com.assessment.ecommerce.controller;

import com.assessment.ecommerce.model.Metrics;
import com.assessment.ecommerce.model.Order;
import com.assessment.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        order.setProcessStartTime(System.currentTimeMillis());
        return orderService.placeOrder(order);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getOrderStatus(@PathVariable Long orderId) {
        return orderService.getOrderStatus(orderId);
    }

    @GetMapping("/metrics")
    public Metrics getMetrics() {
        return orderService.getMetrics();
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Service is running";
    }

}