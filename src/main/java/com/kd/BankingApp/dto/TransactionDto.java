package com.kd.BankingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionDto {
    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;
    private String status;

}
