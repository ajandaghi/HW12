package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.Customers;
import org.bank.entity.TransType;
import org.bank.entity.Transactions;
import org.bank.repository.CustomersRepository;
import org.bank.repository.TransRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionService {
    Connection connection= Connect.getInstance().getConnect();
    TransRepository transRepository;
    AccountService accountService;
    CustomerService customerService;

    public TransactionService() throws SQLException, ClassNotFoundException {
        transRepository=new TransRepository(connection);
        accountService=new AccountService();
        customerService=new CustomerService();
    }


    public void createNewTransaction(Long amount, TransType transType, int customerId, int desCustomerId, String operatorId) throws SQLException {
        Timestamp timestamp =new Timestamp(System.currentTimeMillis());
     Transactions transaction=new Transactions(amount,transType,customerId,desCustomerId,timestamp,operatorId);
     transRepository.insert(transaction);

    }

    public void seerchByDate(String user,String date) throws SQLException, ClassNotFoundException {
        CustomerService customerService=new CustomerService();
      List<Account> acounts=accountService.selectByCustomerId(user,customerService.getLastCustomerId(user));
      if (!acounts.isEmpty()) {
          for (int i = 0; i < acounts.size(); i++) {
              for(int j=0; j<transRepository.searchByDateAndAccountId(acounts.get(i).getId(), Timestamp.valueOf(date + " 00:00:00")).size();j++) {
                  System.out.println(transRepository.searchByDateAndAccountId(acounts.get(i).getId(), Timestamp.valueOf(date + " 00:00:00")).get(j).toString());
              }
          }

          return;
      }
        System.out.println("no transaction found");

    }



    public void seerchByDateAndAccountId(String user, String date, int accountId) throws SQLException, ClassNotFoundException {

        if(customerService.selectCustomerByUser(user).getId()==accountService.selectAccountById(accountId).getCustomerId()) {


            for (int j = 0; j < transRepository.searchByDateAndAccountId(accountService.selectAccountById(accountId).getId(), Timestamp.valueOf(date + " 00:00:00")).size(); j++) {
                System.out.println(transRepository.searchByDateAndAccountId(accountService.selectAccountById(accountId).getId(), Timestamp.valueOf(date + " 00:00:00")).get(j).toString());
            }
        } else
            System.out.println("this account id isnot yours");
    }


}
