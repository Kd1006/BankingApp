package com.kd.BankingApp.repository;

import com.kd.BankingApp.dto.EnquiryRequest;
import com.kd.BankingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
 Boolean existsByEmail(String email);
 Boolean existsByAccountNumber(String accountNumber);
User findByAccountNumber(String accountNumber);

}
