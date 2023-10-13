package com.dnlab.coffeeshop.supllier.repository;

import com.dnlab.coffeeshop.supllier.domain.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
