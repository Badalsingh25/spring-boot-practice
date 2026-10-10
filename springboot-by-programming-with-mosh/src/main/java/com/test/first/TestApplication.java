package com.test.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(TestApplication.class, args);

//        ApplicationContext context = SpringApplication.run(TestApplication.class, args);
//        var orderService = context.getBean(OrderService.class);

//		var orderService = new OrderService(new PaypalPaymentService());

//		var orderService = new OrderService();
//		orderService.setPaymentService(new StripePaymentService());

//        orderService.placeOrder();
    }

}
