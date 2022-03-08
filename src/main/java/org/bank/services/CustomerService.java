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
    private int repeat=0;

    public CustomerService()  {
        customersRepository=new CustomersRepository(connection);
    }

    public void addCustomer(String user, String pass, String nationalId, String fullName, Gender gender, String address)  {
    if(customersRepository.selectByUser(user)==null){
        customersRepository.insert(new Customers(user,pass,nationalId,fullName,gender,address,true));
        System.out.println("customer successfully added");
    } else {
        System.out.println("user is in use");
    }
    }

    public int getLastCustomerId(String user)  {
        if(customersRepository.selectByUser(user)==null){
            return customersRepository.getLastId();
        }
        else
            return customersRepository.selectByUser(user).getId();
    }

    public String getCustomerUserById(int id)  {
        if(customersRepository.selectByCustomerId(id)!=null){
            return customersRepository.selectByCustomerId(id).getUser();
        }
        else
            System.out.println("not such user");
        return  "";
    }

    public Boolean CustomerLogin(String user, String pass)  {

        if (customersRepository.selectByUser(user)!=null) {
            if(!customersRepository.selectByUser(user).getEnable()){
                System.out.println("your customer store.service was diabled!");
            }
            if (repeat==3){
                Customers customer=customersRepository.selectByUser(user);
                customer.setEnable(false);
                customersRepository.updateByUser(user,customer);
                System.out.println("your customer store.service is diabled!");
                return false;
            }


            if(customersRepository.selectByUser(user).getUser().equals(user)&&customersRepository.selectByUser(user).getPass().equals(pass)){
                repeat=0;   return true;
            } else {
                System.out.println("wrong credential");
                repeat++;
            }
        } else {
            System.out.println("user doesn't exist");
        }


        return false;
    }

    public void updateByUser(String user, Customers customers)  {
        customersRepository.updateByUser(user,customers);
        System.out.println("your profile has been updated");
    }

    public Customers selectCustomerByUser(String user)  {
        if(customersRepository.selectByUser(user)!=null) {
            return customersRepository.selectByUser(user);
        } else
            System.out.println("not such user found");
        return null;
    }


}
