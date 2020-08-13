package net.class101.homework1.order;

import lombok.AllArgsConstructor;
import net.class101.homework1.order.controller.OrderController;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoadDataOnStartUp {
    private final OrderController orderController;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        orderController.order();
    }
}
