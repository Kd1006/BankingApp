package com.kd.BankingApp.service;

import com.kd.BankingApp.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
