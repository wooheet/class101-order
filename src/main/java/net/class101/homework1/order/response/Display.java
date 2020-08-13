package net.class101.homework1.order.response;

import net.class101.homework1.order.domain.Product;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class Display {
    public void product(HashMap<Integer, Product> products) {
        Stream<Product> sorted = products.values().stream().sorted(
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

    public void result(double payment, boolean checkKlass, boolean checkKit, double fee) {
        System.out.println("주문금액: " + NumberFormat.getCurrencyInstance(Locale.KOREA).format(payment) + "원");

        if (payment < 50000) {
            if (!(checkKlass && checkKit)) {
                System.out.println("배송비: " + NumberFormat.getCurrencyInstance(Locale.KOREA).format(fee));
            }
            System.out.println("지불금액: "
                    + NumberFormat.getCurrencyInstance(Locale.KOREA).format((payment + fee)) + "원");
        } else {
            System.out.println("지불금액: "
                    + NumberFormat.getCurrencyInstance(Locale.KOREA).format(payment) + "원");
        }
    }

}
