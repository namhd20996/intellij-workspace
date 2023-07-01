package com.example.assign.repo;

import com.example.assign.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {

    Category findCategoryByIdAndStatus(UUID uuid, Integer status);
}
