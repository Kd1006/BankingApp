package com.kd.BankingApp.utils;

import java.time.Year;

public class AccountUtils {
public static final String ACCOUNT_EXISTS_CODE = "001";
public static final String ACCOUNT_EXISTS_MESSAGE = "This email already registered";
public static final String ACCOUNT_CREATION_SUCCESS = "002";
public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created" ;
public static final String ACCOUNT_NOT_EXIST_CODE ="003";
public static final String ACCOUNT_NOT_EXIST_MESSAGE = "User with the provided account number doesn't exist";
public static final  String ACCOUNT_FOUND_CODE = "004";
public static final String ACCOUNT_FOUND_SUCCESS = "User account found" ;
public static final String ACCOUNT_CREDITED_SUCCESS = "005";
public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "User account successfully created!";
public static final String INSUFFICIENT_BALANCE_CODE = "006";
public static final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Value";
public static final String ACCOUNT_DEBITED_SUCCESSFULLY = " 007 ";
public static final String ACCOUNT_DEBITED_SUCCESSFULLY_MESSAGE = "Account has been debited successfully";
public static final String TRANSFER_SUCCESSFUL_CODE = "008";
public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer successful";


    public  static String generatedAccountNumber (){
        // creating account number - assigning random numbers
        //2023 + random 6 digits

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;
        int randNumber= (int) Math.floor(Math.random() * (max - min + 1 ) + min);
// convert the current and random numbers to strings, then concatenate
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);

        return year + randomNumber;

    }



    // generating random number between min and max

}
