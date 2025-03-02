package com.assessment.ecommerce.service;

import com.assessment.ecommerce.model.Order;
import com.assessment.ecommerce.queue.OrderQueue;
import com.assessment.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class OrderProcessor {
    private final OrderQueue orderQueue;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderProcessor(OrderQueue orderQueue, OrderRepository orderRepository) {
        this.orderQueue = orderQueue;
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.getNextOrder();
            if (order != null) {
                System.out.println("Processing Order: " + order.getOrderId());
                order.setStatus("Processing");
                orderRepository.save(order);

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                order.setProcessCompletionTime(System.currentTimeMillis());
                order.setStatus("Completed");
                orderRepository.save(order);
                System.out.println("Order Completed: " + order.getOrderId());
            }
        }
    }
}

