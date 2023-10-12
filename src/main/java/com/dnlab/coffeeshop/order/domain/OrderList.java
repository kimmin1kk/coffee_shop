package com.dnlab.coffeeshop.order.domain;


import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.order.common.PaymentMethod;
import com.dnlab.coffeeshop.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OrderList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderList", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Orders> orders;

    private Integer totalPrice;

    public OrderList(User user) {
        this.user = user;
    }
}
