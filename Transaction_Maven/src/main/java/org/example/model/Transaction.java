package org.example.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    private TransactionType transactionType;
    private String bankAccount;
    private Double amount;
    private String message;
    private Date dateTime;

    public Transaction(TransactionType transactionType, String bankAccount, Double amount, String message, Date dateTime) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Transaction() {
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", bankAccount='" + bankAccount + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
