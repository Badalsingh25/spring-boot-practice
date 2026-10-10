package com.codingshuttle.youtube.learningSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("stripePaymentService")
public class StripePaymentService implements PaymentService{
    
    @Override
    public String pay() {
        String payment = "Stripe Payment";
        System.out.println("Paying From : " + payment);
        return payment;
    }
}
