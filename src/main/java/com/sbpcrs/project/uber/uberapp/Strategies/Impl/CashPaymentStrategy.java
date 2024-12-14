package com.sbpcrs.project.uber.uberapp.Strategies.Impl;

import com.sbpcrs.project.uber.uberapp.Strategies.PaymentStrategy;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.Payment;
import com.sbpcrs.project.uber.uberapp.entities.Wallet;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentStatus;
import com.sbpcrs.project.uber.uberapp.entities.enums.TransactionMethod;
import com.sbpcrs.project.uber.uberapp.repositories.PaymentRepository;
import com.sbpcrs.project.uber.uberapp.services.PaymentService;
import com.sbpcrs.project.uber.uberapp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        Wallet wallet = walletService.findWalletByUser(driver.getUser());
        double platformCommission = wallet.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(wallet.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }

}
