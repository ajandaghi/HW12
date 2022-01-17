package org.bank.entity;

import java.util.ArrayList;
import java.util.List;

public class Customers {
    private int Id;
    private String nationalId;
    private String fullName;
    private Gender gender;
    private String address;
    private List<Account> accounts = new ArrayList<>();
}
