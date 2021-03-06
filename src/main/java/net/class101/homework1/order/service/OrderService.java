package net.class101.homework1.order.service;

import net.class101.homework1.order.domain.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Scanner;

@Service
public interface OrderService {
    void readProdInfo();

    HashMap<Integer, Product> getProd();

    void selectProd();
}



