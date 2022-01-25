package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.AccountType;
import org.bank.entity.Cards;
import org.bank.repository.AccountRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;
    private CustomerService customerService;
    private BranchService branchService;
    private Connection connection= Connect.getInstance().getConnect();

    public AccountService() throws SQLException, ClassNotFoundException {
        accountRepository=new AccountRepository(connection);
        branchService=new BranchService();
        customerService= new CustomerService();
    }

    public  void addAccount(String branchNo , AccountType accountType, String cUser) throws SQLException {
         if(customerService.selectCustomerByUser(cUser)==null){
             return;
         }
        String accountNo=branchNo+"."+accountType.getType()+"."+customerService.getLastCustomerId(cUser);
        int i=1;
        while(accountRepository.selectByAccountNo(accountNo+"-"+i)!=null){

            i++;

        }
        accountNo=accountNo+"-"+i;

        accountRepository.insert(new Account(accountNo,customerService.getLastCustomerId(cUser),branchService.getBranchId(branchNo),accountType,50000L,-1));
        System.out.println("account added");
        }



    public void showAllAccount(String user) throws SQLException, ClassNotFoundException {
         boolean isEmpty=true;
         StaffsActions staffsActions=new StaffsActions();

            for (int i = 0; i < accountRepository.select().size(); i++) {
                if(accountRepository.select().get(i).getBranchId()==staffsActions.getBranchIdByUser(user)) {
                    System.out.println(accountRepository.select().get(i).toString()); isEmpty=false;
                }
            }
            if (isEmpty) System.out.println("no Account in your branch found.");


    }

    public List<Account> selectByCustomerId(String user,int customerId) throws SQLException, ClassNotFoundException {
        StaffsActions staffsActions = new StaffsActions();

        if (!accountRepository.selectByCustomerId(customerId).isEmpty()) {
            for (int i = 0; i < accountRepository.selectByCustomerId(customerId).size(); i++) {
                    System.out.println(accountRepository.selectByCustomerId(customerId).get(i).toString());
            }
            return accountRepository.selectByCustomerId(customerId);

        } else{
            System.out.println("no Account found");
            return null;
        }

    }

    public void deleteAccount(String accountNo) throws SQLException, ClassNotFoundException {
        if (accountRepository.selectByAccountNo(accountNo)!=null) {
            CardService cardService=new CardService();
            Cards card =cardService.selectCardByAccountId(accountRepository.selectByAccountNo(accountNo).getId());
            card.setEnable(false);
            cardService.update(cardService.selectCardByAccountId(accountRepository.selectByAccountNo(accountNo).getId()).getCardNo(),card);
            accountRepository.deleteByAccountNo(accountNo);

            System.out.println("account deleted");
        }
        else
        System.out.println("not such account found.");
    }




    public Account selectByAccountNo(String accountNo) throws SQLException {
        return accountRepository.selectByAccountNo(accountNo);
    }

    public void updateByAccountNo(String accountNo, Account account) throws SQLException {
        accountRepository.updateByAccountNo(accountNo,account);
    }

    public Account selectAccountById(int id) throws SQLException {

        for (int i=0;i<accountRepository.select().size();i++){
            if(accountRepository.select().get(i).getId()==id) {
                return accountRepository.select().get(i);

            }
        }
        return null;
    }
}


