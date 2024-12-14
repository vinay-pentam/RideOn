package com.sbpcrs.project.uber.uberapp.Strategies;

import com.sbpcrs.project.uber.uberapp.entities.Payment;

public interface PaymentStrategy {
     static final double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);

}
