package com.dnlab.coffeeshop.supllier.repository;

import com.dnlab.coffeeshop.supllier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByName(String name);
}
