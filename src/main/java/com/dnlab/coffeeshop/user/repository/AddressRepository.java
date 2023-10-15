package com.dnlab.coffeeshop.user.repository;

import com.dnlab.coffeeshop.user.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
