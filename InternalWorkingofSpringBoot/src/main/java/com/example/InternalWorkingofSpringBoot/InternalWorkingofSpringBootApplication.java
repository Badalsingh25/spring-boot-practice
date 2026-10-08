package com.example.InternalWorkingofSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternalWorkingofSpringBootApplication implements CommandLineRunner {
	public static void main(String[] args) {
        SpringApplication.run(InternalWorkingofSpringBootApplication.class, args);
	}

    private final PaymentService paymentService;

    public InternalWorkingofSpringBootApplication(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void run(String... args) throws Exception {
        String payment = paymentService.pay();
        System.out.println("Payment done:" + payment);
    }
}
