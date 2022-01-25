package org.bank;

import org.bank.entity.AccountType;
import org.bank.entity.Gender;
import org.bank.repository.CardRepository;
import org.bank.services.AccountService;
import org.bank.services.CardService;
import org.bank.services.StaffsActions;
import org.bank.services.CustomerService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {


        Date date= new java.sql.Date(System.currentTimeMillis());
        System.out.println(date.getYear()+1900);
        System.out.println(date.getMonth());
    }
}
