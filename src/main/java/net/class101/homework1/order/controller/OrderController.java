package net.class101.homework1.order.controller;

import lombok.RequiredArgsConstructor;
import net.class101.homework1.order.service.OrderService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    public void order() {
        orderService.selectProd();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
//        String selectOder = scanner.nextLine();
//
//        if (selectOder.equals("o")) {
//            basket = new HashMap<>();
//            HashMap<Integer, Product> products = orderService.getProd();
//            display.product(products);
//
//            while (true) {
//                System.out.print("상품번호: ");
//                String prodNum = scanner.nextLine();
//
//                if (prodNum.compareTo(" ") == 0) {
//                    double payment = 0;
//                    System.out.println("주문내역:");
//                    System.out.println("------------------------------------");
//
//                    for (Integer key : basket.keySet()) {
//                        if (products.get(key).getKlass() != null) {
//                            Klass klass = products.get(key).getKlass();
//                            System.out.println(klass.getName() + " - " + basket.get(key) + "개");
//                            payment +=klass.getPrice() * basket.get(key);
//                        }
//
//                        if (products.get(key).getKit() != null) {
//                            Kit kit = products.get(key).getKit();
//                            System.out.println(kit.getName() + " - " + basket.get(key) + "개");
//                            payment +=kit.getPrice() * basket.get(key);
//
//                            int prodTotalQuantity = products.get(key).getKit().getQuantity();
//
//                            if (prodTotalQuantity < basket.get(key)) {
//                                throw new SoldOutException("상품의 재고가 부족합니다.");
//                            }
//                        }
//                    }
//
//                    System.out.println("------------------------------------");
//                    //cal service
//
//                    System.out.println("주문금액: " + payment + "원");
//
//                    //TODO: 키트 상품의 경우, 주문 금액이 5만원 미만일 경우 배송료 5,000원이 추가되어야 합니다
//                    // 클래스와 키트를 함께 주문할 시 배송료는 포함되지 않아야 합니다
//                    if (payment < 50000) {
//                        System.out.println("배송비: 5,000원");
//                        System.out.println("지불금액: " + (payment + DELIVERY_FEE) + "원");
//                    } else {
//                        System.out.println("지불금액: " + payment + "원");
//                    }
//
//                    order();
//                    break;
//                }
//
//                if (products.containsKey(Integer.parseInt(prodNum))) {
//                    if (basket.containsKey(Integer.parseInt(prodNum))
//                            && products.get(Integer.parseInt(prodNum)).getKlass() != null) {
//                        System.out.println("주문하신 클래스는 이미 주문함에 포함되어 있습니다.");
//                        continue;
//                    }
//
//                    System.out.print("수량: ");
//                    String quantity = scanner.nextLine();
//
//                    if (products.get(Integer.parseInt(prodNum)).getKlass() != null) {
//                        basket.put(Integer.parseInt(prodNum), Integer.parseInt(quantity));
//                    }
//
//                    if (products.get(Integer.parseInt(prodNum)).getKit() != null) {
//                        basket.put(Integer.parseInt(prodNum),
//                                basket.containsKey(Integer.parseInt(prodNum))
//                                        ? basket.get(Integer.parseInt(prodNum)) + Integer.parseInt(quantity)
//                                        : Integer.parseInt(quantity));
//                    }
//
//                } else {
//                    System.out.println("주문 하신 상품번호가 존재하지 않습니다.");
//                }
//            }
//        } else if (selectOder.equals("q")){
//            System.out.println("고객님의 주문 감사합니다.");
//            System.exit(0);
//        } else {
//            System.out.println("o 또는 q를 입력해주세요.");
//            System.exit(0);
//        }
//
//        scanner.close();
    }

    public void preprocessing() {
        orderService.readProdInfo();
    }
}
