package com.codingshuttle.youtube.learningSpringBoot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorpayPaymentService implements PaymentService{
    
    @Override
    public String pay() {
        String payment = "Razorpay Payment";
        System.out.println("Paying From : " + payment);
        return payment;
    }
}
