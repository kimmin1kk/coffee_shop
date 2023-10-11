package com.dnlab.coffeeshop.user.repository;

import com.dnlab.coffeeshop.user.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
