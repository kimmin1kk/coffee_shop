package com.dnlab.coffeeshop.product.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.common.Category;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Integer price;

    @Column(nullable = false)
    private Category category;
}
