package net.class101.homework1.order.repository;

import net.class101.homework1.order.domain.Kit;
import net.class101.homework1.order.domain.Klass;
import net.class101.homework1.order.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ProductRepo {
    private Product product;
    private static HashMap<Integer, Product> repo;

    public static void setRepo(HashMap<Integer, Product> repo) {
        ProductRepo.repo = repo;
    }

    public void saveKit(int prodNum, Kit kit){
        product = Product.builder()
                .prodNum(prodNum)
                .kit(kit)
                .build();

        repo.put(prodNum, product);
    }

    public void saveKlass(int prodNum, Klass klass) {
        product = Product.builder()
                .prodNum(prodNum)
                .klass(klass)
                .build();

        repo.put(prodNum, product);
    }
}
