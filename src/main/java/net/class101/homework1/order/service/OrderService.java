package net.class101.homework1.order.service;

import net.class101.homework1.order.domain.Product;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public interface OrderService {
    void readProdInfo();

    HashSet<Product> selectProd();
}



