package com.jddev.productmicroservice.repository;

import com.jddev.productmicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
