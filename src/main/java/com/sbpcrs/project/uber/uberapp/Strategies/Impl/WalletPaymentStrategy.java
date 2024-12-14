package com.sbpcrs.project.uber.uberapp.Strategies.Impl;

import com.sbpcrs.project.uber.uberapp.Strategies.PaymentStrategy;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Payment;
import com.sbpcrs.project.uber.uberapp.entities.Rider;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.TransactionMethod;
import com.sbpcrs.project.uber.uberapp.repositories.PaymentRepository;
import com.sbpcrs.project.uber.uberapp.services.PaymentService;
import com.sbpcrs.project.uber.uberapp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Rider rider = payment.getRide().getRider();
        Driver driver = payment.getRide().getDriver();

        Long riderId = rider.getId();
        Long driverId = driver.getId();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null,
                payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driverCut, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }
}
