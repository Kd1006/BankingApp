package com.kd.BankingApp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    private String transactionId;
    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;


}
