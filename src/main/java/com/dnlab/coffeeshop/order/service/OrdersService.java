package com.dnlab.coffeeshop.order.service;

import com.dnlab.coffeeshop.order.common.OrderPageForm;
import com.dnlab.coffeeshop.order.common.OrderState;
import com.dnlab.coffeeshop.order.domain.OrderContent;
import com.dnlab.coffeeshop.order.domain.Orders;
import com.dnlab.coffeeshop.order.repository.OrderContentRepository;
import com.dnlab.coffeeshop.order.repository.OrdersRepository;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.repository.ProductRepository;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderContentRepository orderContentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * 상품 하나의 유효성을 검사하는 로직
     * checkCart 메서드 내부에서 사용
     * OrderContent Seq 넣어야함
     */
    public boolean checkQuantity(Long seq) {
        Optional<OrderContent> orderContentOptional = orderContentRepository.findById(seq);
//        return orderContentOptional.filter(orderContent -> orderContent.getCount() <= orderContent.getProduct().getQuantity()).isPresent();
        return true;
    }

    /**
     * 리스트(orders -> orderContentList)를 넣어 내부의 모든 상품의 유효성을 검사하는 로직
     */

    public boolean checkCart(Orders orders) {
        for (OrderContent product : orders.getOrderContentList()) {
            if (!checkQuantity(product.getSeq())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Param으로 Orders를 받아 검사하는 로직
     * confirmOrder 메서드 내부에서 사용
     */
    @Transactional
    public void processOrder(Orders orders) {
        List<OrderContent> orderContentList = new ArrayList<>();
        for (OrderContent orderContent : orders.getOrderContentList()) { // 재고량 -- 하는 과정
            Product product = orderContent.getProduct();
//            product.setQuantity(product.getQuantity() - orderContent.getCount());
            productRepository.save(product);
            orderContentList.add(orderContent);
        }

    }

    /**
     * checkCart로 먼저 주문이 가능한 상태인지 확인
     * processOrder에서 값이 정상인지 확인
     */
    @Transactional
    public void confirmOrder(OrderPageForm orderPageForm, Orders orders) {
        if (checkCart(orders)) {
            processOrder(orders);
            Orders modifiedOrders = orders.toBuilder()
                    .orderState(OrderState.PREPARING)
//                    .postalCode(orderPageForm.getPostalCode())
//                    .defaultAddress(orderPageForm.getDefaultAddress())
//                    .detailAddress(orderPageForm.getDetailAddress())
//                    .cardNumber(orderPageForm.getCardNumber())
//                    .cardType(orderPageForm.getCardType())
                    .ordered(true)
                    .build();
            ordersRepository.save(modifiedOrders);
        }
    }

    /**
     * isOrdered == True인 장바구니 리스트를 찾는 로직.
     *
     * @param username (Principal.getName())
     * @return List<Orders> orderedCarts
     */
    public List<Orders> getOrderedCarts(String username) {
        var user = userRepository.findByUsername(username);
        List<Orders> orderedCarts = new ArrayList<>();
        for (Orders selectOrderedCart : user.getOrdersList()) {
            if (selectOrderedCart.isOrdered()) {
                orderedCarts.add(selectOrderedCart);
            }
        }
        return orderedCarts;
    }

    public List<Orders> getAllOrderList() {
        List<Orders> ordersList = new ArrayList<>();
        for (Orders selectOrdersList : ordersRepository.findAll()) {
            if (selectOrdersList.isOrdered()) {
                ordersList.add(selectOrdersList);
            }
        }
        return ordersList;
    }
}
