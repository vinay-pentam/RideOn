package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.entities.Ride;
import com.sbpcrs.project.uber.uberapp.entities.User;
import com.sbpcrs.project.uber.uberapp.entities.Wallet;
import com.sbpcrs.project.uber.uberapp.entities.WalletTransaction;
import com.sbpcrs.project.uber.uberapp.entities.enums.TransactionMethod;
import com.sbpcrs.project.uber.uberapp.entities.enums.TransactionType;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.repositories.WalletRepository;
import com.sbpcrs.project.uber.uberapp.services.WalletService;
import com.sbpcrs.project.uber.uberapp.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    @Override
    public Wallet createWallet(User user) {
       Wallet wallet = new Wallet();
       wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findWalletByUser(user);
        wallet.setAmount(wallet.getAmount() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.CREDIT)
                .wallet(wallet)
                .ride(ride)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        walletTransactionService.createTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findWalletByUser(user);
        wallet.setAmount(wallet.getAmount() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.DEBIT)
                .wallet(wallet)
                .ride(ride)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        walletTransactionService.createTransaction(walletTransaction);

//        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }
    @Override
    public void withdrawAllWalletAmount(Long userId) {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
       return walletRepository.findById(walletId).orElseThrow(
               () -> new ResourceNotFoundException("Wallet not found with id : "+ walletId)
       );
    }


    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(
                ()-> new ResourceNotFoundException("Wallet not found for user with id : "+ user.getId())
        );
    }
}
