package org.bank.services;

import org.bank.Connect;
import org.bank.entity.AccountType;
import org.bank.entity.Customers;
import org.bank.entity.Gender;
import org.bank.repository.CustomersRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerService {
    private CustomersRepository customersRepository;
    private Connection connection= Connect.getInstance().getConnect();

    public CustomerService() throws SQLException, ClassNotFoundException {
        customersRepository=new CustomersRepository(connection);
    }

    public void addCustomer(String user, String pass, String nationalId, String fullName, Gender gender, String address) throws SQLException {
    if(customersRepository.selectByUser(user)==null){
        customersRepository.insert(new Customers(user,pass,nationalId,fullName,gender,address));
    } else {
        System.out.println("user is in use");
    }
    }

    public int getCustomerId(String user) throws SQLException {
        if(customersRepository.selectByUser(user)==null){
            return customersRepository.getLastId();
        }
        else
            return customersRepository.selectByUser(user).getId();
    }


}
