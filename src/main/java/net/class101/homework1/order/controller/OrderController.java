package net.class101.homework1.order.controller;

import lombok.RequiredArgsConstructor;
import net.class101.homework1.order.domain.Product;
import net.class101.homework1.order.service.OrderService;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public void order() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String selectOder = scanner.nextLine();

        if (selectOder.equals("o")) {
            HashSet<Product> products = orderService.selectProd();
            displayProduct(products);

            while (true) {
                System.out.print("상품번호: ");
                //TODO: 동일 클래스 추가 주문 시 중복 주문하는 것을 막아주어야 합니다, 클래스 수량 1개
                // 결제가 완료되고, 다음 주문에선 이전에 결제와 무관하게 클래스 주문이 되어야 합니다
                String prodNum = scanner.nextLine();

                if (prodNum.compareTo(" ") == 0) {
                    //TODO: 키트 상품의 경우, 주문 금액이 5만원 미만일 경우 배송료 5,000원이 추가되어야 합니다
                    // 클래스와 키트를 함께 주문할 시 배송료는 포함되지 않아야 합니다

                    //TODO 주문이 완료 되었을 경우, 주문 내역과 주문 금액, 결제 금액 (배송비 포함) 을 화면에 display 합니다
                    System.out.println("주문내역:");
                    System.out.println("------------------------------------");
                    System.out.println();
                    System.out.println();
                    System.out.println("------------------------------------");
                    System.out.println("주문금액: " + "원");
                    System.out.println("지불금액: " + "원");
                    order();
                    break;
                }

                System.out.print("수량: ");
                //TODO: 결제 시 재고확인을 하여야 하며 재고가 부족할 경우 결제를 시도하면 **SoldOutException**이 발생되어야 합니다
                String quantity = scanner.nextLine();
            }
        } else if (selectOder.equals("q")){
            System.out.println("고객님의 주문 감사합니다.");
            System.exit(0);
        } else {
            System.out.println("o 또는 q를 입력해주세요.");
            order();
        }


        scanner.close();
    }

    public void preprocessing() {
        orderService.readProdInfo();
    }

    public void displayProduct(HashSet<Product> products) {
        Stream<Product> sorted = products.stream().sorted(
                Comparator.comparingLong(Product::getProdNum).reversed());

        System.out.println("상품번호 \t\t\t\t\t 상품명 \t\t\t\t\t\t\t 판매가격 \t\t 재고수");

        sorted.forEach(product -> {
            if (product.getKit() != null) {
                System.out.println(product.getProdNum() + "  " + product.getKit().getName() + "  "
                        + product.getKit().getPrice() + "  " + product.getKit().getQuantity());
            }

            if (product.getKlass() != null) {
                System.out.println(product.getProdNum() + "  " + product.getKlass().getName() + "  "
                        + product.getKlass().getPrice() + "  " + product.getKlass().getQuantity());
            }
        });
    }
}
