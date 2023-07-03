package com.example.assign.repo;

import com.example.assign.entity.Category;
import com.example.assign.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    List<Product> findAllByCategory(Category category);
}
