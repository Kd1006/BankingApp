package com.kd.BankingApp.service;

import com.kd.BankingApp.dto.TransactionDto;
import com.kd.BankingApp.entities.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);

}
