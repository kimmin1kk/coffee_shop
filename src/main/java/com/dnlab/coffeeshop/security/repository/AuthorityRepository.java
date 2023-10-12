package com.dnlab.coffeeshop.security.repository;

import com.dnlab.coffeeshop.security.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
