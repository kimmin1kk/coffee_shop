package com.dnlab.coffeeshop.order.service;

import com.dnlab.coffeeshop.order.common.OrderPageForm;
import com.dnlab.coffeeshop.order.domain.OrderContent;
import com.dnlab.coffeeshop.order.domain.Orders;
import com.dnlab.coffeeshop.order.repository.OrdersRepository;
import com.dnlab.coffeeshop.product.repository.IngredientRepository;
import com.dnlab.coffeeshop.product.repository.ProductRepository;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    /**
     * 상품 하나의 유효성을 검사하는 로직
     * checkCart 메서드 내부에서 사용
     * OrderContent Seq 넣어야함
     */
    public AtomicBoolean checkQuantityValidation(List<OrderContent> list) {
        AtomicBoolean check = new AtomicBoolean(true);
        list.forEach(orderContent -> {
            orderContent.getProduct().getRecipeList().forEach(
                    recipe -> {
                        if (ingredientRepository.findByName(recipe.getIngredient().getName()).getAmount() < orderContent.getCount() * recipe.getAmount()) {
                            check.set(false);
                        }

                    }
            );
        });
        return check;
    }




    /**
     * 재고량 -- 하는 로직
     * @param list
     */
    public void minusIngredientAmount(List<OrderContent> list) {
        list.forEach(orderContent -> {
            orderContent.getProduct().getRecipeList().forEach(
                    recipe -> ingredientRepository.findByName(recipe.getIngredient().getName())
                            .minusAmount(recipe.getAmount() * orderContent.getCount())); //상품별 레시피 리스트
                }
        );
    }

    /**
     * Param으로 Orders를 받아 검사하는 로직
     * confirmOrder 메서드 내부에서 사용
     */
    @Transactional
    public void processOrder(Orders orders) {
        orders.getOrderContentList().stream()
                .map(OrderContent::getProduct)
                .forEach(productRepository::save);
    }

    /**
     * checkCart로 먼저 주문이 가능한 상태인지 확인
     * processOrder에서 값이 정상인지 확인
     */
    @Transactional
    public void confirmOrder(OrderPageForm orderPageForm, Orders orders) {
        if (checkQuantityValidation(orders.getOrderContentList()).get()) {
            minusIngredientAmount(orders.getOrderContentList());
            processOrder(orders);
            orders.confirmOrder(orderPageForm);
        }else {
            throw new RuntimeException("Error: 재고 부족으로 인한 상품 주문 불가");
        }
    }

    /**
     * isOrdered == True인 장바구니 리스트를 찾는 로직.
     *
     * @param username (Principal.getName())
     * @return List<Orders> orderedCarts
     */
    public List<Orders> getOrderedCarts(String username) {

        return userRepository.findByUsername(username).getOrdersList().stream()
                .filter(Orders::isOrdered)
                .toList();
    }

    /**
     * ordered가 True 인 주문 리스트 반환
     *
     * @return List<Orders>
     */
    public List<Orders> getAllOrderList() {
        return ordersRepository.findAll().stream()
                .filter(Orders::isOrdered)
                .toList();
    }

}
