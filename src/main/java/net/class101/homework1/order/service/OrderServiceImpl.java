package net.class101.homework1.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.class101.homework1.order.domain.Kit;
import net.class101.homework1.order.domain.Klass;
import net.class101.homework1.order.repository.KitRepo;
import net.class101.homework1.order.repository.KlassRepo;
import net.class101.homework1.order.type.ProductType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final KitRepo kitRepo;
    private final KlassRepo klassRepo;

    @Override
    public void readProdInfo() {
        ClassPathResource resource = new ClassPathResource("data/productInfo.txt");

        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);

            for (int i = 1; i < content.size(); i++) {
                String[] prodInfo = content.get(i).split(",");

                int prodNum = Integer.parseInt(prodInfo[0].trim());
                String type = prodInfo[1].trim();
                double price = Double.parseDouble(prodInfo[prodInfo.length - 2].trim());
                int quantity = Integer.parseInt(prodInfo[prodInfo.length - 1].trim());

                String name;
                if (prodInfo.length > 5) {
                    List<String> prodInfoList = new ArrayList<>(Arrays.asList(prodInfo));
                    name = String.join("", extractionName(prodInfoList)).trim();

                } else {
                    name = prodInfo[2].trim();
                }

                save(prodNum, type, name, price, quantity);
            }
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
        }
    }

    private void save(int prodNum, String type, String name, double price, int quantity) {
        if (type.equals(ProductType.KLASS.name())) {
            Klass klass = Klass.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
            klassRepo.saveKlass(prodNum, klass);
        }

        if (type.equals(ProductType.KIT.name())) {
            Kit kit = Kit.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
            kitRepo.saveKit(prodNum, kit);
        }
    }

    @Override
    public void selectProd() {

    }

    static List<String> extractionName(List<String> prodInfoList) {
        return prodInfoList.subList(2, prodInfoList.size() - 2);
    }
}
