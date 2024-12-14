package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.WalletTransactionDTO;
import com.sbpcrs.project.uber.uberapp.entities.WalletTransaction;

public interface WalletTransactionService {

    WalletTransaction createTransaction(WalletTransaction walletTransaction);

}
