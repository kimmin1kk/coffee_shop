package com.dnlab.coffeeshop.user.repository;

import com.dnlab.coffeeshop.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
