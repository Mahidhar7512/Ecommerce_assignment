package com.assessment.ecommerce.queue;

import com.assessment.ecommerce.model.Order;
import org.springframework.stereotype.Component;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class OrderQueue {
    private final Queue<Order> queue = new ConcurrentLinkedQueue<>();

    public void addOrder(Order order) {
        queue.add(order);
        System.out.println("Order added to queue: " + order.getOrderId());
    }

    public Order getNextOrder() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
