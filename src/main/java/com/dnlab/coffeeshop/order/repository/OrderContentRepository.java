package com.dnlab.coffeeshop.order.repository;

import com.dnlab.coffeeshop.order.domain.OrderContent;
import com.dnlab.coffeeshop.order.domain.Orders;
import com.dnlab.coffeeshop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContent, Long> {
    Optional<OrderContent> findByOrdersAndProduct(Orders orders, Product product);

}
