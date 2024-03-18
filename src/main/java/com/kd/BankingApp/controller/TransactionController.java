package com.kd.BankingApp.controller;


import com.itextpdf.text.DocumentException;
import com.kd.BankingApp.entities.Transaction;
import com.kd.BankingApp.service.BankStatement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankStatement")
@AllArgsConstructor


public class TransactionController {

    private BankStatement bankStatement;

    @GetMapping
    public List<Transaction> generateBankStatement(@RequestParam String accountNumber,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate ) throws DocumentException, FileNotFoundException {
        return bankStatement.generateBankStatement(accountNumber, startDate,endDate);
    }


}
