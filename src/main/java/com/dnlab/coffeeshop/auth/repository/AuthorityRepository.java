package com.dnlab.coffeeshop.auth.repository;

import com.dnlab.coffeeshop.auth.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
