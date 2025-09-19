package org.springclass.onlinebankingsystem.controller.response;

import lombok.Getter;
import lombok.Setter;
import org.springclass.onlinebankingsystem.repository.entity.Transaction;

import java.util.Date;

@Getter
@Setter
public class TransactionResponse {
    private Long id;
    private Date transactionDate;
    private String transactionId;
    private String transactionType;
    private String fromAccountNumber;
    private String toAccountNumber;
    private Double debitAmount;
    private String currency;
    private Double requestAmount;
    private String requestCurrency;
    private String description;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.transactionDate = transaction.getTransactionDate();
        this.transactionId = transaction.getTransactionId();
        this.transactionType = transaction.getTransactionType().name();
        this.fromAccountNumber = transaction.getFromAccount().getAccountNumber();
        this.toAccountNumber = transaction.getToAccount().getAccountNumber();
        this.debitAmount = transaction.getAmount();
        this.currency = transaction.getCurrency().name();
        this.requestAmount = transaction.getAmount();
        this.requestCurrency = transaction.getCurrency().name();
        this.description = transaction.getDescription();
    }
}
