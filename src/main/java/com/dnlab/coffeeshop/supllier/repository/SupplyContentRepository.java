package com.dnlab.coffeeshop.supllier.repository;

import com.dnlab.coffeeshop.supllier.domain.SupplyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyContentRepository extends JpaRepository<SupplyContent, Long> {
    List<SupplyContent> findSupplyContentsBySupplySeq(Long seq);

}
