package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.entities.Payment;
import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentStatus;
import com.sbpcrs.project.uber.uberapp.repositories.PaymentRepository;
import com.sbpcrs.project.uber.uberapp.services.PaymentService;
import com.sbpcrs.project.uber.uberapp.services.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(()-> new RuntimeException("Payment object not found for ride with id : "+ ride.getId()));
        paymentStrategyManager.getPaymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
       Payment payment = Payment.builder()
               .paymentMethod(ride.getPaymentMethod())
               .paymentStatus(PaymentStatus.PENDING)
               .amount(ride.getFare())
               .ride(ride)
               .build();
       return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
