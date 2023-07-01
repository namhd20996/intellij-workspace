package com.example.assign.repo;

import com.example.assign.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {

    List<Order> findAllByIdAndStatus(UUID id, Integer status);


}
