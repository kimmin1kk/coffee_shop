package com.dnlab.coffeeshop.order.service;

import com.dnlab.coffeeshop.order.domain.OrderContent;
import com.dnlab.coffeeshop.order.domain.Orders;
import com.dnlab.coffeeshop.order.repository.OrderContentRepository;
import com.dnlab.coffeeshop.order.repository.OrdersRepository;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.repository.ProductRepository;
import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderContentService {
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderContentRepository orderContentRepository;


    @Transactional
    public void deleteOrders(Long seq) {
        Orders cart = ordersRepository.findBySeq(seq);
        if (cart.isInstant()) {
            ordersRepository.deleteById(seq);
        }

    }

    /**
     * 장바구니 구매용
     * Orders 리스트를 가져와서 isOrdered == false && isInstant == false인 Orders의 Seq로 찾는 로직
     *
     * @param username
     * @return 장바구니
     */
    public Orders findOrders(String username) {
        Orders cart = null;
        for (Orders findCart : ordersRepository.findOrdersListByUserUsername(username)) {
            if (!findCart.isOrdered() && !findCart.isInstant()) {
                cart = findCart;
            }
        }
        return cart;
    }


    /**
     * 바로 구매용
     * Orders 리스트를 가져와서 isOrdered == false && isInstant == true인 Orders의 Seq로 찾는 로직
     *
     * @param username
     * @return 장바구니
     */
    public Orders findOrdersForInstant(String username) {
        Orders cart = null;
        for (Orders findCart : ordersRepository.findOrdersListByUserUsername(username)) {
            if (!findCart.isOrdered() && findCart.isInstant()) {
                cart = findCart;
            }
        }
        return cart;
    }

    /**
     * 장바구니 구매용
     * 조건이 맞으면 장바구니 생성
     * 기존에 주문내역이 있는 유저 = 마지막 장바구니 isOrdered == True && isInstant == false 일 경우 새로 생성
     * 처음 주문하는 유저 = OrdersList.isEmpty -> 새로 생성
     */
    public void getOrders(String username) {
        boolean check = true;
        var user = userRepository.findByUsername(username);

        if (!user.getOrdersList().isEmpty()) { //장바구니가 이미 있엇으면 이걸로 ㅇㅇ
            for (Orders orders : user.getOrdersList()) {
                if (!orders.isInstant()) {
                    check = orders.isOrdered();
                }
            }
            if (check) {
                createOrders(username);
            }
        }
        if (user.getOrdersList().isEmpty()) { //첫 장바구니 생성
            createOrders(username);
        }
    }

    /**
     * 바로 구매용
     * 조건에 맞게 장바구니 생성
     * 기존에 주문내역이 있는 유저 = 마지막 장바구니 isOrdered == True && isInstant == true 일 경우 새로 생성
     * 처음 주문하는 유저 = OrdersList.isEmpty -> 새로 생성
     */
    public void getOrdersForInstant(String username) {
        boolean check = true;
        var user = userRepository.findByUsername(username);

        if (!user.getOrdersList().isEmpty()) { //장바구니가 이미 있엇으면 이걸로 ㅇㅇ
            Long seq = null;
            for (Orders orders : user.getOrdersList()) {
                check = orders.isOrdered();
                seq = orders.getSeq();
            }
            if (check) {
                createOrdersForInstant(username);
            } else {
                deleteOrders(seq);
                createOrdersForInstant(username);
            }
        }
        if (user.getOrdersList().isEmpty()) { //첫 장바구니 생성
            createOrdersForInstant(username);
        }
    }

    /**
     * 장바구니 구매용
     * 장바구니 생성
     *
     * @param username
     */
    public void createOrders(String username) {
        var user = userRepository.findByUsername(username);
        var orders = new Orders(user);
        ordersRepository.save(orders);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orders);
    }

    /**
     * 바로 구매용
     * 장바구니 생성
     *
     * @param username
     */
    public void createOrdersForInstant(String username) {
        var user = userRepository.findByUsername(username);
        var orders = new Orders(user).toBuilder()
                .instant(true)
                .build();
        ordersRepository.save(orders);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orders);
    }


    /**
     * 장바구니 구매용
     *
     * @param username
     * @return 합계액
     */
    public int findTotalPrice(String username) {
        var orders = findOrders(username);
        List<OrderContent> orderContentList = orders.getOrderContentList();
        if (!orderContentList.isEmpty()) {
            return orderContentList.stream()
                    .mapToInt(i -> i.getProduct().getPrice() * i.getCount())
                    .sum();
        }
        else {
            return 0;
        }

    }

    /**
     * 장바구니 구매용
     *
     * @param seq      상품 PK
     * @param username
     * @param count    구매하려는 상품 수
     */
    public void addProductToCart(long seq, String username, int count) {
        getOrders(username);

        var orders = findOrders(username);

        Optional<Product> product = productRepository.findById(seq);

        if (product.isPresent()) { //Optional<Product>기 때문에 isPresent 메서드를 통해 검증하는 과정이 있어야함
            OrderContent existingOrderContent = orderContentRepository.findByOrdersAndProduct(orders, product.get());

            if (existingOrderContent == null) { //이미 장바구니에 있는 상품일 경우, 카운트만 올라가게 하는 로직
                OrderContent newOrderContent = new OrderContent(orders, product.get(), count);
                orderContentRepository.save(newOrderContent);
            } else {
                int updatedCount = existingOrderContent.getCount() + count;
                existingOrderContent.setCount(updatedCount);
                orderContentRepository.save(existingOrderContent);
            }
        }
    }

    /**
     * 바로 구매용
     *
     * @param seq   상품 PK
     * @param count 구매하려는 상품 수
     */
    public void addProductToCartForInstant(long seq, String username, int count) {

        var orders = findOrdersForInstant(username);

        Optional<Product> product = productRepository.findById(seq);

        if (product.isPresent()) { //Optional<Product>기 때문에 isPresent 메서드를 통해 검증하는 과정이 있어야함

            OrderContent newOrderContent = new OrderContent(orders, product.get(), count);
            orders.getOrderContentList().add(newOrderContent);
            orderContentRepository.save(newOrderContent);
        }
    }
}
