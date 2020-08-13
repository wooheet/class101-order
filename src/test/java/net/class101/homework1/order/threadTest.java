package net.class101.homework1.order;

import net.class101.homework1.order.response.Display;
import net.class101.homework1.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class threadTest extends Thread{

    @Autowired
    private OrderService orderService;

    @Autowired
    private Display display;

    @Test
    void threadTest() {
        while(true) {
            System.out.println("Thread: come on give me a string!");
            try { sleep(5000); } catch(InterruptedException ignored) {}
        }
    }

//    @Test
//    public void shouldTakeUserInput() {
//        Input inputOutput= new Input();
//
//        String input = "o";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        assertEquals("o", inputOutput.getInput());
//    }
}
