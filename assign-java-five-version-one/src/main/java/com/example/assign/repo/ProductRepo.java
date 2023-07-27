package com.example.assign.repo;

import com.example.assign.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Query(""" 
            SELECT o FROM Product o WHERE o.category.id = ?1
            """)
    Optional<List<Product>> findAllByCategoryId(UUID id);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Product o SET o.quantity=?1 WHERE o.id=?2 AND o.status=?3
            """)
    void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status);

}
