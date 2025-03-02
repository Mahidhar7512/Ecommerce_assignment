package com.assessment.ecommerce.model;

public class Metrics {
    private long totalOrders;
    private long pendingOrders;
    private long processingOrders;
    private long completedOrders;
    private long averageProcessingTime; // In milliseconds

    public Metrics(long totalOrders, long pendingOrders, long processingOrders, long completedOrders, long avgProcessingTime) {
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.processingOrders = processingOrders;
        this.completedOrders = completedOrders;
        this.averageProcessingTime = avgProcessingTime;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public long getProcessingOrders() {
        return processingOrders;
    }

    public long getCompletedOrders() {
        return completedOrders;
    }

    public long getAverageProcessingTime() {
        return averageProcessingTime;
    }
}

