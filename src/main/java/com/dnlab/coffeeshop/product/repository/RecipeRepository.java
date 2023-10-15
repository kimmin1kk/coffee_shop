package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
