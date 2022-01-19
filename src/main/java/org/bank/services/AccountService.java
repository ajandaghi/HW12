package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.AccountType;
import org.bank.repository.AccountRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountService {
    private AccountRepository accountRepository;
    private CardService cardService;
    private CustomerService customerService;
    private BranchService branchService;
    private Connection connection= Connect.getInstance().getConnect();

    public AccountService() throws SQLException, ClassNotFoundException {
        accountRepository=new AccountRepository(connection);
        cardService=new CardService();
        branchService=new BranchService();
        customerService= new CustomerService();
    }

    public  void addAccount(String branchNo , AccountType accountType, String cUser) throws SQLException {

        String accountNo=branchNo+"."+accountType.getType()+"."+customerService.getCustomerId("cUser");
        int i=1;
        while(accountRepository.selectByAccountNo(accountNo+"-"+i)!=null){

            i++;

        }
        accountNo=accountNo+"-"+i;

        accountRepository.insert(new Account(accountNo,customerService.getCustomerId("cUser"),branchService.getBranchId(branchNo),accountType,50000L, true));

        }

    public  void addCardToAccount(String accountNo,String expDate,String pass2) throws SQLException, ParseException {
        cardService.addCard(accountRepository.selectByAccountNo(accountNo).getId(),new Date(new SimpleDateFormat("YYYY/MM").parse(expDate).getTime()),pass2);
    }

}
