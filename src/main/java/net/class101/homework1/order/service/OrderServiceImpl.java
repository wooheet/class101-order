package net.class101.homework1.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.class101.homework1.order.domain.Kit;
import net.class101.homework1.order.domain.Klass;
import net.class101.homework1.order.domain.Product;
import net.class101.homework1.order.exception.SoldOutException;
import net.class101.homework1.order.repository.ProductRepo;
import net.class101.homework1.order.response.Display;
import net.class101.homework1.order.type.ProductType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Display display;
    private final ProductRepo productRepo;
    private static final double DELIVERY_FEE = 5000;

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
        Kit kit = null;
        Klass klass = null;

        if (type.equals(ProductType.KLASS.name())) {
            klass = Klass.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
        }

        if (type.equals(ProductType.KIT.name())) {
            kit = Kit.builder()
                    .name(name)
                    .price(price)
                    .quantity(quantity)
                    .build();
        }

        Product product = Product.builder()
                .prodNum(prodNum)
                .klass(klass)
                .kit(kit)
                .build();

        productRepo.setProduct(product);
    }

    @Override
    public HashMap<Integer, Product> getProd() {
        return productRepo.getProductRepo();
    }

    @Override
    public void selectProd() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String selectOder = scanner.nextLine();

        if (selectOder.equals("o")) {
            HashMap<Integer, Integer> basket = new HashMap<>();
            HashMap<Integer, Product> products = getProd();
            display.product(products);

            while (true) {
                System.out.print("상품번호: ");
                String prodNum = scanner.nextLine();

                if (prodNum.compareTo(" ") == 0) {
                    double payment = 0;
                    boolean checkKlass = false;
                    boolean checkKit = false;

                    System.out.println("주문내역:");
                    System.out.println("------------------------------------");

                    for (Integer key : basket.keySet()) {
                        if (products.get(key).getKlass() != null) {
                            Klass klass = products.get(key).getKlass();
                            System.out.println(klass.getName() + " - " + basket.get(key) + "개");
                            payment +=klass.getPrice() * basket.get(key);
                            checkKlass = true;
                        }

                        if (products.get(key).getKit() != null) {
                            Kit kit = products.get(key).getKit();
                            System.out.println(kit.getName() + " - " + basket.get(key) + "개");
                            payment +=kit.getPrice() * basket.get(key);

                            int prodTotalQuantity = products.get(key).getKit().getQuantity();

                            if (prodTotalQuantity < basket.get(key)) {
                                throw new SoldOutException("상품의 재고가 부족합니다.");
                            }
                            checkKit = true;
                        }
                    }
                    System.out.println("------------------------------------");

                    display.result(payment, checkKlass, checkKit, DELIVERY_FEE);

                    selectProd();
                    break;
                }

                if (products.containsKey(Integer.parseInt(prodNum))) {
                    if (basket.containsKey(Integer.parseInt(prodNum))
                            && products.get(Integer.parseInt(prodNum)).getKlass() != null) {
                        System.out.println("주문하신 클래스는 이미 주문함에 포함되어 있습니다.");
                        continue;
                    }

                    System.out.print("수량: ");
                    String quantity = scanner.nextLine();

                    if (products.get(Integer.parseInt(prodNum)).getKlass() != null) {
                        basket.put(Integer.parseInt(prodNum), Integer.parseInt(quantity));
                    }

                    if (products.get(Integer.parseInt(prodNum)).getKit() != null) {
                        basket.put(Integer.parseInt(prodNum),
                                basket.containsKey(Integer.parseInt(prodNum))
                                        ? basket.get(Integer.parseInt(prodNum)) + Integer.parseInt(quantity)
                                        : Integer.parseInt(quantity));
                    }

                } else {
                    System.out.println("주문 하신 상품번호가 존재하지 않습니다.");
                }
            }
        } else if (selectOder.equals("q")){
            System.out.println("고객님의 주문 감사합니다.");
            System.exit(0);
        } else {
            System.out.println("o 또는 q를 입력해주세요.");
            System.exit(0);
        }

        scanner.close();
    }

    static List<String> extractionName(List<String> prodInfoList) {
        return prodInfoList.subList(2, prodInfoList.size() - 2);
    }
}
