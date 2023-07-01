package com.example.assign.repo;

import com.example.assign.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, UUID> {
}
