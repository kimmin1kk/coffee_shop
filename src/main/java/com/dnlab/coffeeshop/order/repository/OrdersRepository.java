package com.dnlab.coffeeshop.order.repository;

import com.dnlab.coffeeshop.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findBySeq(Long seq);

    List<Orders> findOrdersListByUserUsername(String username);
}
