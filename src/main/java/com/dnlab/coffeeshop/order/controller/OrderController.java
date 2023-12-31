package com.dnlab.coffeeshop.order.controller;

import com.dnlab.coffeeshop.order.common.OrderPageForm;
import com.dnlab.coffeeshop.order.service.OrderContentService;
import com.dnlab.coffeeshop.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrdersService ordersService;
    private final OrderContentService orderContentService;

    /**
     * 장바구니에서 결제하기를 클릭했을 때 호출되는 컨트롤러
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        model.addAttribute("orders", orderContentService.findOrders(principal.getName()));
        return "shop/orderPage";
    }

    /**
     * 바로구매를 클릭했을 때 호출되는 컨트롤러
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/add-to-cart-instant/{seq}")
    public String orderPageForInstant(Model model, Principal principal, @PathVariable("seq") Long seq, @RequestParam(value = "count", required = false) Integer count) {
        orderContentService.getOrdersForInstant(principal.getName());
        if (count == null) {
            count = 1;
        }
        orderContentService.addProductToCartForInstant(seq, principal.getName(), count);

        model.addAttribute("orders", orderContentService.findOrdersForInstant(principal.getName()));
        return "shop/orderPage";
    }

    @PostMapping("/order-page")
    public String orderPage(Model model, Principal principal, @ModelAttribute OrderPageForm orderPageForm) {
        log.info("OrderController -> orderPage : OK , orderPageForm is " + orderPageForm.toString());

        if (orderPageForm.isOrderInstant()) {
            ordersService.confirmOrder(orderPageForm, orderContentService.findOrdersForInstant(principal.getName()));
        } else {
            ordersService.confirmOrder(orderPageForm, orderContentService.findOrders(principal.getName()));
        }

        return "redirect:/";
    }

    @GetMapping("/order-history")
    public String orderHistoryPage(Model model, Principal principal) {
        model.addAttribute("orderedCarts", ordersService.getOrderedCarts(principal.getName()));
        return "account/orderHistory";
    }

    @GetMapping("/order-list")
    public String orderList(Model model, Principal principal) {
        model.addAttribute("orders", ordersService.getAllOrderList());
        log.info(ordersService.getAllOrderList() + "sss");
        return "shop/orderList";
    }

}
