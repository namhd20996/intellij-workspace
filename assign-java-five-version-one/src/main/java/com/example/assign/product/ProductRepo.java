package com.example.assign.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<com.example.assign.product.Product, UUID> {

    Optional<Product> findProductByIdAndStatus(UUID uuid, Integer status);

    boolean existsByName(String name);

    @Query(""" 
            SELECT o FROM Product o WHERE o.category.id = ?1
            """)
    Optional<List<com.example.assign.product.Product>> findAllByCategoryId(UUID id);

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE Product o SET o.quantity=?1 WHERE o.id=?2 AND o.status=?3
            """)
    void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status);

}
