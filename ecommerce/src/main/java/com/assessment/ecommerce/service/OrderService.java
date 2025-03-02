package com.assessment.ecommerce.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.assessment.ecommerce.model.Metrics;
import com.assessment.ecommerce.model.Order;
import com.assessment.ecommerce.queue.OrderQueue;
import com.assessment.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderQueue orderQueue;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderQueue orderQueue) {
        this.orderRepository = orderRepository;
        this.orderQueue = orderQueue;
    }

    public Order placeOrder(Order order) {
        System.out.println("placeorder is called");
        order.setStatus("Pending");
        Order savedOrder = orderRepository.save(order);
        orderQueue.addOrder(savedOrder);
        return savedOrder;
    }

    public Optional<Order> getOrderStatus(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Metrics getMetrics() {
        long totalOrders = orderRepository.count();
        long pendingOrders = orderRepository.countByStatus("Pending");
        long processingOrders = orderRepository.countByStatus("Processing");
        long completedOrders = orderRepository.countByStatus("Completed");

        List<Order> completedOrdersList = orderRepository.findByStatus("Completed");
        long totalProcessingTime = 0;
        int processedCount = completedOrdersList.size();

        for (Order order : completedOrdersList) {
            if (order.getProcessingStartTime() >0 && order.getCompletionTime() >0) {
                totalProcessingTime += order.getCompletionTime() - order.getProcessingStartTime();
            }
        }

        long avgProcessingTime = (processedCount > 0) ? totalProcessingTime / processedCount : 0;

        return new Metrics(totalOrders, pendingOrders, processingOrders, completedOrders, avgProcessingTime);
    }
}
