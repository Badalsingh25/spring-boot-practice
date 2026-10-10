package com.test.first;

import org.springframework.stereotype.Service;

@Service
public class PaypalPaymentService implements PaymentService {

	@Override
	public void processPayment(double amount) {
		System.out.println("-------PAYPAL PAYMENT----------");
		System.out.println("Amount:" + amount);
	}
}
