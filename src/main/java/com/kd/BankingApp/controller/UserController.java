package com.kd.BankingApp.controller;

import com.itextpdf.text.DocumentException;
import com.kd.BankingApp.dto.*;
import com.kd.BankingApp.entities.Transaction;
import com.kd.BankingApp.entities.User;
import com.kd.BankingApp.repository.TransactionRepository;
import com.kd.BankingApp.repository.UserRepository;
import com.kd.BankingApp.service.BankStatement;
import com.kd.BankingApp.service.EmailService;
import com.kd.BankingApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account Management APIs")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    EmailService emailService;
    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user account ad assigning an account ID"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }
    @PostMapping("login")
    public BankResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);

    }

    @Operation(
            summary = "Balance Enquiry",
            description = "Given an account number, shows the current balance "
    )

    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);

    }

    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request) {
        return userService.creditAccount(request);
    }
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request){
        return  userService.debitAccount(request);
    }
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request){
        return userService.transfer(request);
    }
    @GetMapping("bankStatement")
    public List<Transaction> generateBankStatement(@RequestParam String accountNumber,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate ) throws DocumentException, FileNotFoundException {
        BankStatement bankStatement = new BankStatement(transactionRepository , userRepository,  emailService);
        return bankStatement.generateBankStatement(accountNumber, startDate,endDate);
    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<User> deleteById(@PathVariable Long id){
//        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity <User> updateById(@PathVariable Long id, @RequestBody UpdateRequest updateRequest){
//        return new ResponseEntity<>(userService.updateById(id, updateRequest), HttpStatus.OK);
//
//    }

}


