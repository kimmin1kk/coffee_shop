package com.dnlab.coffeeshop.user.repository;

import com.dnlab.coffeeshop.user.domain.PointUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointUsageRepository extends JpaRepository<PointUsage, Long> {
    List<PointUsage> findPointUsagesByUserUsername(String username);
}
