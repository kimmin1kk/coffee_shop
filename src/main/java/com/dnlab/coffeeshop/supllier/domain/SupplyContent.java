package com.dnlab.coffeeshop.supllier.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.common.Unit;
import com.dnlab.coffeeshop.product.domain.Ingredient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class SupplyContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "supply_seq")
    private Supply supply;

    @ManyToOne
    @JoinColumn(name = "ingredient_seq")
    private Ingredient ingredient;

}
