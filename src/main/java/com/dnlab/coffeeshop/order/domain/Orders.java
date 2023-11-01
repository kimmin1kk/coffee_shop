package com.dnlab.coffeeshop.order.domain;


import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.order.common.OrderState;
import com.dnlab.coffeeshop.order.common.PaymentMethod;
import com.dnlab.coffeeshop.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderContent> orderContentList;

    private Integer totalPrice;

    private OrderState orderState;

    @Builder.Default
    private boolean instant = false;

    @Builder.Default
    private boolean ordered = false;

    public Orders(User user) {
        this.user = user;
    }

}
