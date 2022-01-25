package org.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class Customers {
    private int Id;
    private String user;
    private String pass;
    private String nationalId;
    private String fullName;
    private Gender gender;
    private String address;
    private Boolean isEnable;
    //private List<Account> accounts = new ArrayList<>();

    public Customers(String user, String pass, String nationalId, String fullName, Gender gender, String address, Boolean isEnable) {
        this.user = user;
        this.pass = pass;
        this.nationalId = nationalId;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.isEnable=isEnable;
    }

    public Customers() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }
}
