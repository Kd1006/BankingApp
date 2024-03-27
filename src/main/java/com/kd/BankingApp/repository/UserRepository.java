package com.kd.BankingApp.repository;

import com.kd.BankingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
//// void deleteById(Long id);
// User updateById(Long id);
 Boolean existsByEmail(String email);
 Optional<User> findByEmail (String email);
 Boolean existsByAccountNumber(String accountNumber);
 User findByAccountNumber(String accountNumber);




}
