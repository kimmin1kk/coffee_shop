package com.dnlab.coffeeshop.supllier.repository;

import com.dnlab.coffeeshop.supllier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
