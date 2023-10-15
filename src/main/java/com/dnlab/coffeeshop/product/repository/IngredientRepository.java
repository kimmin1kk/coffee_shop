package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
