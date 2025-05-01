package com.vue.example.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.vue.example.erpsystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p FROM Product p JOIN p.tags t " +
            "WHERE (:keyword IS NULL OR p.name LIKE %:keyword%) " +
            "AND (:status IS NULL OR p.status = :status) " +
            "AND (:tags IS NULL OR t.name IN :tags)")
     List<Product> searchProducts(
             @Param("keyword") String keyword,
             @Param("status") String status,
             @Param("tags") List<String> tags
     );
}