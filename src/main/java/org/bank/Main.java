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


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        CustomerService customerService=new CustomerService();
        customerService.addCustomer("ali","1234","009","aliJandaghi", Gender.Male,"tehran");

        StaffsActions staffsActions =new StaffsActions();
        staffsActions.addBranch("200","tehran","tehran");


        staffsActions.addEmployee("abbass","1234","abbas mehri","200");

       AccountService accountService=new AccountService();
      // accountService.addAccount("200", AccountType.Saving,"ali");

        CardService cardService=new CardService();
        cardService.addCardToAccount("200.100.1-1","2022/09","1234");
        CardRepository cardRepository=new CardRepository(Connect.getInstance().getConnect());
        System.out.println(cardRepository.selectByCardNo("5835-7648-3689-5251").getCvv2());

    }
}
