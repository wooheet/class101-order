package net.class101.homework1.order.controller;

import lombok.RequiredArgsConstructor;
import net.class101.homework1.order.service.OrderService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public void order() {
        orderService.selectProd();
    }

    public void preprocessing() {
        orderService.readProdInfo();
    }
}
