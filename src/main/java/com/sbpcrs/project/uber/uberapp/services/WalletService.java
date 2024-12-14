package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.User;
import com.sbpcrs.project.uber.uberapp.entities.Wallet;
import com.sbpcrs.project.uber.uberapp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet createWallet(User user);

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllWalletAmount(Long userId);

    Wallet findWalletById(Long walletId);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet findWalletByUser(User user);
}
