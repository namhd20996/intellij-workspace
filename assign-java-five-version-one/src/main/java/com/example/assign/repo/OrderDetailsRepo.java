package com.example.assign.repo;

import com.example.assign.entity.OrderDetails;
import com.example.assign.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, UUID> {


    @Query("""
            SELECT o FROM OrderDetails o WHERE o.order.email = ?1
            """)
    List<OrderDetails> findProductsByEmail(String email);
}
