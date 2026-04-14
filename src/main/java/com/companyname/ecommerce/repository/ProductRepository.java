package com.companyname.ecommerce.repository;

import com.companyname.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByProductNameContainingIgnoreCase(String keyword);
    //JPQL(Java Persistence Query Language)
    //Auto-generated JPQL by Spring:
    //SELECT p FROM Product p WHERE LOWER(P.productName) LIKE LOWER(CONCAT('%', :keyword, '%'));
}
