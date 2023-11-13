package com.dnlab.coffeeshop.order.domain;


import com.dnlab.coffeeshop.config.BaseTimeEntity;
import com.dnlab.coffeeshop.order.common.OrderPageForm;
import com.dnlab.coffeeshop.order.common.PaymentMethod;
import com.dnlab.coffeeshop.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<OrderContent> orderContentList = new ArrayList<>();

    private Integer totalPrice;

    @Builder.Default
    private boolean instant = false;

    @Builder.Default
    private boolean ordered = false;

    public Orders(User user) {
        this.user = user;
    }

    public void confirmOrder(OrderPageForm pageForm) {
        this.paymentMethod = pageForm.getPaymentMethod();
        this.totalPrice = this.orderContentList.stream().mapToInt(oc -> oc.getProduct().getPrice() * oc.getCount()).sum() - pageForm.getPoint();
        this.ordered = true;
    }
}
