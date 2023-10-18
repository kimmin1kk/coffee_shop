package com.dnlab.coffeeshop.product.repository;

import com.dnlab.coffeeshop.product.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findRecipesByProductSeq(Long seq);
}
