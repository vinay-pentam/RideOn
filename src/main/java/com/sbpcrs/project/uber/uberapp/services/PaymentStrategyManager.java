package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.Strategies.Impl.CashPaymentStrategy;
import com.sbpcrs.project.uber.uberapp.Strategies.Impl.WalletPaymentStrategy;
import com.sbpcrs.project.uber.uberapp.Strategies.PaymentStrategy;
import com.sbpcrs.project.uber.uberapp.entities.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CollectionIdMutability;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }

}
