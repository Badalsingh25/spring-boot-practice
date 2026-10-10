package com.codingshuttle.youtube.learningSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentChoose {

    @Autowired
    @Qualifier("stripePaymentService")
    private PaymentService paymentService;
    
//    private final PaymentService paymentService;
//
//    public PaymentChoose(@Qualifier("stripePaymentService") PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
    
    public String paymentOptionChoose(){
        return paymentService.pay();
    }
}
