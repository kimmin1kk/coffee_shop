package com.dnlab.coffeeshop.order.domain;

import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.product.domain.Product;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "order_list_seq")
    private OrderList orderList;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;

    private Integer count;




}
