package org.bank.entity;

import java.sql.Timestamp;

public class Transactions {
    private int id;
   // private int transId;
    private Long amount;
    private TransType transType;
    private int accountId;
    private int desAccountId;
    private Timestamp dateTime;
    private String operator;

    public Transactions(Long amount, TransType transType, int customerId, int desCustomerId, Timestamp dateTime, String operatorId) {
        this.amount = amount;
        this.transType = transType;
        this.accountId = customerId;
        this.desAccountId = desCustomerId;
        this.dateTime = dateTime;
        this.operator = operatorId;
    }

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TransType getTransType() {
        return transType;
    }

    public void setTransType(TransType transType) {
        this.transType = transType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getDesAccountId() {
        return desAccountId;
    }

    public void setDesAccountId(int desAccountId) {
        this.desAccountId = desAccountId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", amount=" + amount +
                ", transType=" + transType +
                ", accountId=" + accountId +
                ", desAccountId=" + desAccountId +
                ", dateTime=" + dateTime +
                ", operator='" + operator + '\'' +
                '}';
    }
}
