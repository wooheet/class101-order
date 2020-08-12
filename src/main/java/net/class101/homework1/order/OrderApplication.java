package net.class101.homework1.order;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.class101.homework1.order.controller.OrderController;
import net.class101.homework1.order.domain.Kit;
import net.class101.homework1.order.domain.Klass;
import net.class101.homework1.order.domain.Product;
import net.class101.homework1.order.repository.KitRepo;
import net.class101.homework1.order.repository.KlassRepo;
import net.class101.homework1.order.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
