package net.class101.homework1.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.class101.homework1.order.controller.OrderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableCaching
@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) { SpringApplication.run(OrderApplication.class, args); }

	@AllArgsConstructor
	static class Order {
		private final OrderController orderController;

		@Bean
		private void saveProduceInfo() {
			orderController.preprocessing();
		}

		@Bean
		public void init() {
			orderController.order();
		}
	}

}
