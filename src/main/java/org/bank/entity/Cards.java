package org.bank.entity;

import java.sql.Date;

public class Cards {
private int id;
private int accountId;
private String cardNo;
private String cvv2;
private Date expDate;
private String pass2;
private Boolean isEnable;

    public Cards(int accountId, String cardNo, String cvv2, Date expDate, String pass2, Boolean isEnable) {
        this.accountId = accountId;
        this.cardNo = cardNo;
        this.cvv2 = cvv2;
        this.expDate = expDate;
        this.pass2 = pass2;
        this.isEnable = isEnable;
    }

    public Cards() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }
}
