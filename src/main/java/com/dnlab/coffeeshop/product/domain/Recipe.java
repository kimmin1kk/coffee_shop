package com.dnlab.coffeeshop.product.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.common.Unit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Recipe extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "ingredient_seq")
    private Ingredient ingredient;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne
    @JoinColumn(name="product_seq")
    private Product product;
}