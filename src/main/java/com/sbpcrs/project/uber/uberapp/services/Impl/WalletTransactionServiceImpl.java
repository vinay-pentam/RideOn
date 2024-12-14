package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.WalletTransactionDTO;
import com.sbpcrs.project.uber.uberapp.entities.WalletTransaction;
import com.sbpcrs.project.uber.uberapp.repositories.WalletTransactionRepository;
import com.sbpcrs.project.uber.uberapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final ModelMapper modelMapper;
    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public WalletTransaction createTransaction(WalletTransaction walletTransaction) {
        return walletTransactionRepository.save(walletTransaction);
    }
}
