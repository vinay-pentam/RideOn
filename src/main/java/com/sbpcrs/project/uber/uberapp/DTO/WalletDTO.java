package com.sbpcrs.project.uber.uberapp.DTO;

import com.sbpcrs.project.uber.uberapp.entities.WalletTransaction;
import lombok.Data;

import java.util.List;

@Data
public class WalletDTO {

    private Long id;

    private UserDTO user;

    private Double amount;

    private List<WalletTransaction> transactions;
}
