package com.kd.BankingApp.repository;

import com.kd.BankingApp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {


}
