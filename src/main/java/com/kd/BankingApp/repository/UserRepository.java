package com.kd.BankingApp.repository;

import com.kd.BankingApp.dto.EnquiryRequest;
import com.kd.BankingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
 Boolean existsByEmail(String email);
 Optional<User> findByEmail (String email);
 Boolean existsByAccountNumber(String accountNumber);
User findByAccountNumber(String accountNumber);


}
