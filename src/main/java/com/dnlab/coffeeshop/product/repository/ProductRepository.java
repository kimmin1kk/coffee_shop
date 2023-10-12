package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}