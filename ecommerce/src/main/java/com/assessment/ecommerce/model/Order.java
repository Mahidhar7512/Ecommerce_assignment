package com.assessment.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    @ElementCollection
    private List<Long> itemIds;
    @Getter
    @Setter
    private Double totalAmount;
    @Getter
    @Setter
    private String status;
    @Setter
    @Transient
    private long processStartTime;
    @Setter
    @Transient
    private long processCompletionTime;
    public Order() {}

    public Order(Long userId, List<Long> itemIds, Double totalAmount, String status) {
        this.userId = userId;
        this.itemIds = itemIds;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public long getProcessingStartTime() {
        return processStartTime;
    }

    public long getCompletionTime() {
        return processCompletionTime;
    }
}

