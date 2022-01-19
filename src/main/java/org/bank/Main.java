package org.bank;

import org.bank.entity.AccountType;
import org.bank.entity.Gender;
import org.bank.services.AccountService;
import org.bank.services.BossActions;
import org.bank.services.CustomerService;

import java.sql.SQLException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        CustomerService customerService=new CustomerService();
        customerService.addCustomer("ali","1234","009","aliJandaghi", Gender.Male,"tehran");

        BossActions bossActions =new BossActions();
        bossActions.addBranch("200","tehran","tehran");


        bossActions.addEmployee("abbass","1234","abbas mehri","200");

       AccountService accountService=new AccountService();
      // accountService.addAccount("200",AccountType.Saving,"ali");

       accountService.addCardToAccount("200.100.1-1","2022/09","1234");
    }
}
