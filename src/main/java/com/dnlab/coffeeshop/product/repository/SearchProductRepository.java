package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.common.Category;
import com.dnlab.coffeeshop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByNameContaining(String keyword);

    List<Product> findProductByCategory(Category category);

    List<Product> findProductByNameContainingAndCategory(String keyword, Category category);
}
