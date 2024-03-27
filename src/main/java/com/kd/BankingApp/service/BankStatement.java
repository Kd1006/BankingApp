package com.kd.BankingApp.service;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kd.BankingApp.dto.EmailDetails;
import com.kd.BankingApp.entities.Transaction;
import com.kd.BankingApp.entities.User;
import com.kd.BankingApp.repository.TransactionRepository;
import com.kd.BankingApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {
    // retrieve list of transactions given date range and account number
    // generate a pdf file of transaction
    // send the file via email

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private EmailService emailService;
    private static final String FILE = "/Users/kd/Documents/MyStatement.pdf";
    public List<Transaction> generateBankStatement(String accountNumber, String startDate, String endDate) throws FileNotFoundException, DocumentException {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        System.out.println("Parsed start date " + start);
        System.out.println("Parsed end date " + end);

        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> {
                    LocalDateTime transactionDate = transaction.getCreatedAt();
                    System.out.println("Transaction date: " + transactionDate);
                    return !transactionDate.toLocalDate().isBefore(start) && !transactionDate.toLocalDate().isAfter(end);
                })
                .collect(Collectors.toList());


        User user = userRepository.findByAccountNumber(accountNumber);
        String customerName = user.getFirstName() + user.getLastName() + user.getOtherName();

        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        log.info("Setting size of documents");
        try (OutputStream outputStream = new FileOutputStream(FILE)) {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            PdfPTable bankInfoTable = new PdfPTable(1);
            PdfPCell bankName = new PdfPCell(new Phrase("KDBank "));
            bankName.setBorder(0);
            bankName.setBackgroundColor(new BaseColor(65, 105, 225));
            bankName.setPadding(20f);

            PdfPCell bankAddress = new PdfPCell(new Phrase("1100 Wilson Blvd, Arlington, VA"));
            bankName.setBorder(0);
            bankInfoTable.addCell(bankName);
            bankInfoTable.addCell(bankAddress);


            PdfPTable statementInfo = new PdfPTable(2);
            PdfPCell customerInfo = new PdfPCell(new Phrase(startDate));
            customerInfo.setBorder(0);

            PdfPCell statement = new PdfPCell(new Phrase("Bank Statement"));
            statement.setBorder(0);

            PdfPCell stopDate = new PdfPCell(new Phrase(endDate));
            stopDate.setBorder(0);


            System.out.println("Transaction List size: " + transactionList.size());

            PdfPCell name = new PdfPCell(new Phrase("Account Holder : " + customerName));
            name.setBorder(0);
            PdfPCell space = new PdfPCell();
            PdfPCell address = new PdfPCell(new Phrase("Address " + " " + user.getAddress()));
            address.setBorder(0);
            address.setBackgroundColor(new BaseColor(255, 218, 185));

            PdfPTable transactionTable = new PdfPTable(4);
            PdfPCell date = new PdfPCell(new Phrase("DATE"));
            date.setBackgroundColor(new BaseColor(255, 218, 185));
            date.setBorder(0);
            PdfPCell transactionType = new PdfPCell(new Phrase("Transaction Type"));
            transactionType.setBorder(0);
            PdfPCell transactionAmount = new PdfPCell(new Phrase("Transaction Amount"));
            transactionAmount.setBorder(0);

            PdfPCell status = new PdfPCell(new Phrase("Status"));

            status.setBackgroundColor((new BaseColor(173, 216, 230)));
            status.setBorder(0);

            transactionTable.addCell(date);
            transactionTable.addCell(transactionType);
            transactionTable.addCell(transactionAmount);
            transactionTable.addCell(status);

            transactionList.forEach(transaction -> {
                transactionTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
                transactionTable.addCell(new Phrase(transaction.getTransactionType()));
                transactionTable.addCell(new Phrase(transaction.getAmount().toString()));
                transactionTable.addCell(new Phrase(transaction.getStatus()));
            });

            statementInfo.addCell(customerInfo);
            statementInfo.addCell(statement);
            statementInfo.addCell(endDate);
            statementInfo.addCell(name);
            statementInfo.addCell(space);
            statementInfo.addCell(address);

            document.add(bankInfoTable);
            document.add(statementInfo);
            document.add(transactionTable);
            document.close();

            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(user.getEmail())
                    .subject("Bank Statement")
                    .messageBody("Kindly find your requested statement attached ")
                    .attachment(FILE)
                    .build();

            emailService.sendEmailWithAttachment(emailDetails);

        } catch (DocumentException | IOException e) {
            log.error("Error generating a bank statement");
        }

        return transactionList;

    }
}







//package com.kd.BankingApp.service;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.kd.BankingApp.dto.EmailDetails;
//import com.kd.BankingApp.entities.Transaction;
//import com.kd.BankingApp.entities.User;
//import com.kd.BankingApp.repository.TransactionRepository;
//import com.kd.BankingApp.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//@Slf4j
//public class BankStatement {
//    private TransactionRepository transactionRepository;
//    private UserRepository userRepository;
//    private EmailService emailService;
//    private static final BaseColor LIGHT_COLOR = new BaseColor(240, 240, 240);
//    private static final BaseColor DARK_BLUE_COLOR = new BaseColor(0, 0, 139);
//    private static final String FILE = "/Users/kd/Documents/MyStatement.pdf";
//
//    public List<Transaction> generateBankStatement(String accountNumber, String startDate, String endDate)
//            throws FileNotFoundException, DocumentException {
//        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//        List<Transaction> transactionList = transactionRepository.findAll().stream()
//                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
//                .filter(transaction -> {
//                    LocalDateTime transactionDate = transaction.getCreatedAt();
//                    return !transactionDate.toLocalDate().isBefore(start) && !transactionDate.toLocalDate().isAfter(end);
//                })
//                .collect(Collectors.toList());
//
//        User user = userRepository.findByAccountNumber(accountNumber);
//        String customerName = user.getFirstName() + user.getLastName() + user.getOtherName();
//
//        Rectangle statementSize = new Rectangle(PageSize.A4);
//        Document document = new Document(statementSize);
//        log.info("Setting size of documents");
//        try (OutputStream outputStream = new FileOutputStream(FILE)) {
//            PdfWriter.getInstance(document, outputStream);
//            document.open();
//
//            PdfPTable bankInfoTable = createTableWithCell("KDBank", "1100 Wilson Blvd, Arlington, VA");
//
//            PdfPTable statementInfo = createTableWithCell("Start Date: " + startDate, "Bank Statement", "End Date: " + endDate);
//            PdfPTable customerInfoTable = createTableWithCell("Account Holder: " + customerName, "Address: " + user.getAddress());
//            PdfPTable transactionTable = createTransactionTable(transactionList);
//
//            document.add(bankInfoTable);
//            document.add(statementInfo);
//            document.add(customerInfoTable);
//            document.add(transactionTable);
//            document.close();
//        } catch (DocumentException | IOException e) {
//            log.error("Error generating a bank statement", e);
//        }
//
//        return transactionList;
//    }
//
//    private PdfPTable createTableWithCell(String... cellTexts) {
//        PdfPTable table = new PdfPTable(cellTexts.length);
//        for (String text : cellTexts) {
//            PdfPCell cell = new PdfPCell(new Phrase(text));
//            cell.setBorder(0);
//            cell.setBackgroundColor(LIGHT_COLOR);
//            table.addCell(cell);
//        }
//        return table;
//    }
//
//    private PdfPTable createTransactionTable(List<Transaction> transactionList) {
//        PdfPTable table = new PdfPTable(3);
//
//        PdfPCell dateHeader = createHeaderCell("DATE", LIGHT_COLOR);
//        PdfPCell transactionTypeHeader = createHeaderCell("Transaction Type", LIGHT_COLOR);
//        PdfPCell transactionStatusHeader = createHeaderCell("Transaction Status", LIGHT_COLOR);
//        table.addCell(dateHeader);
//        table.addCell(transactionTypeHeader);
//        table.addCell(transactionStatusHeader);
//
//        transactionList.forEach(transaction -> {
//            table.addCell(new Phrase(transaction.getCreatedAt().toString()));
//            table.addCell(new Phrase(transaction.getTransactionType()));
//            table.addCell(new Phrase(transaction.getStatus()));
//        });
//
//        return table;
//    }
//
//    private PdfPCell createHeaderCell(String text, BaseColor backgroundColor) {
//        PdfPCell cell = new PdfPCell(new Phrase(text));
//        cell.setBackgroundColor(backgroundColor);
//        cell.setBorder(0);
//        return cell;
//    }
//}



