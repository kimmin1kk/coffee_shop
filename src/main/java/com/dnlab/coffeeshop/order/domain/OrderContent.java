package com.dnlab.coffeeshop.order.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OrderContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "order_seq")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

}
