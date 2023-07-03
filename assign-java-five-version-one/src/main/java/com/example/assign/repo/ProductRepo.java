package com.example.assign.repo;

import com.example.assign.entity.Category;
import com.example.assign.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Query(""" 
            SELECT o FROM Product o WHERE o.category.id = ?1
            """)
    List<Product> findAllByCategoryId(UUID id);
}
