package com.codingshuttle.youtube.learningSpringBoot;

import com.codingshuttle.youtube.learningSpringBoot.service.PaymentChoose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LearningSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootApplication.class, args);
//		ApplicationContext context = SpringApplication.run(LearningSpringBootApplication.class, args);
//
//        PaymentChoose paymentChoose = context.getBean(PaymentChoose.class);
//        String option = paymentChoose.paymentOptionChoose();
//        System.out.println(option);
	}
 
}
