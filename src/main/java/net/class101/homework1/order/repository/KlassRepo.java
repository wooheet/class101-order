package net.class101.homework1.order.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.class101.homework1.order.domain.Klass;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
@AllArgsConstructor
public class KlassRepo extends ProductRepo {
    private final HashMap<Integer, Klass> repo;

    @Override
    public void saveKlass(int prodNum, Klass klass) {
        super.saveKlass(prodNum, klass);
        repo.put(prodNum, klass);
    }
}
