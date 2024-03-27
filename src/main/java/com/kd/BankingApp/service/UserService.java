package com.kd.BankingApp.service;

import com.kd.BankingApp.dto.*;

public interface UserService {
   BankResponse createAccount(UserRequest userRequest);
   BankResponse balanceEnquiry(EnquiryRequest request);
   String nameEnquiry(EnquiryRequest request);
   BankResponse creditAccount(CreditDebitRequest request);
   BankResponse debitAccount(CreditDebitRequest request);
   BankResponse transfer(TransferRequest request);
   BankResponse login(LoginDto loginDto);
//   void deleteById(Long id);
//   BankResponse updateById(Long id, UpdateRequest request);













}
