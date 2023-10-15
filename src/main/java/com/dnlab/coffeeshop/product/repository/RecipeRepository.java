package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
