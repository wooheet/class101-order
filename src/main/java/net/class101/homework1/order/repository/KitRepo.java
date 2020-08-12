package net.class101.homework1.order.repository;

import lombok.AllArgsConstructor;
import net.class101.homework1.order.domain.Kit;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@AllArgsConstructor
public class KitRepo extends ProductRepo {
    private final HashMap<Integer, Kit> repo;

    @Override
    public void saveKit(int prodNum, Kit kit) {
        super.saveKit(prodNum, kit);
        repo.put(prodNum, kit);
    }
}
