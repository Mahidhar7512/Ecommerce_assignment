package com.assessment.ecommerce.repository;

import com.assessment.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    long countByStatus(String status);
    List<Order> findByStatus(String status);
}
