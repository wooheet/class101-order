package net.class101.homework1.order.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.class101.homework1.order.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashSet;


@Getter
@Repository
@AllArgsConstructor
public class ProductRepo {
    private final HashSet<Product> productRepo;

    public void setProduct(Product product) {
        this.productRepo.add(product);
    }
}
