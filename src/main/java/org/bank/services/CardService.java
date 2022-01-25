package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Account;
import org.bank.entity.Cards;
import org.bank.entity.TransType;
import org.bank.repository.CardRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class CardService {
   private CardRepository cardRepository;
    private Connection connection= Connect.getInstance().getConnect();
    private AccountService accountService;
    private  CustomerService customerService;
    private  TransactionService transactionService;
    private  int repeat=0;
    public CardService() throws SQLException, ClassNotFoundException {
        cardRepository=new CardRepository(connection);
        accountService=new AccountService();
        customerService=new CustomerService();
        transactionService=new TransactionService();
    }

    public String addCard(int accountId, Date expDate,String pass2) throws SQLException {

        Random random = new Random();

        if(cardRepository.searchCardByAccountId(accountId)>0 ){
            return "";
        }

        int i=1;
        String cardNo= (random.nextInt(8999)+1000)+"-"+(random.nextInt(8999)+1000)+"-"+(random.nextInt(8999)+1000)+"-"+ (random.nextInt(899)+100);;

        while(cardRepository.selectByCardNo(cardNo+i)!=null){
            i++;

        }
       // System.out.println((cardNo + i).length());
        cardRepository.insert(new Cards(accountId,cardNo+i,String.valueOf(random.nextInt(899)+100),expDate,pass2,true));
        return (cardNo + i);
    }

    public int cardToCard(String user, String fromCard,  String toCard, Long amount, String cvv2, String pass2, String exp) throws SQLException {
        Date date=new Date(System.currentTimeMillis());
        if(cardRepository.selectByCardNo(fromCard)==null ) {
            System.out.println("no such card found");

            return 0;
        } else if(accountService.selectAccountById(cardRepository.selectByCardNo(fromCard).getAccountId()).getCustomerId()!= customerService.getLastCustomerId(user)){
            System.out.println("the card doesn't belong to you ");
            return -1;

        } else if(cardRepository.selectByCardNo(toCard)==null){
            System.out.println("destination card no doesn't exists.");
            return -2;
        } else if (accountService.selectAccountById(cardRepository.selectByCardNo(fromCard).getAccountId()).getBalance()<(amount+600)) {
            System.out.println("not sufficient balance");
            return -3;
        } else if (!cardRepository.selectByCardNo(fromCard).getCvv2().trim().equals(cvv2)){
            System.out.println("your cvv2 isnot valid");
            return -4;
        } else if (!cardRepository.selectByCardNo(fromCard).getPass2().equals(pass2)){
            System.out.println("wrong pass2");
            repeat++;
            return -5;
        } else if(!cardRepository.selectByCardNo(fromCard).getEnable())   {
            System.out.println("your card is not active:");
            return -6;
        } else if (repeat==3) {
            Cards card=cardRepository.selectByCardNo(fromCard);
            card.setEnable(false);
            cardRepository.updateByCardNo(fromCard,card);
            System.out.println("wrong pass2 3 time card is deactivated");
            return -7;
        } else if((cardRepository.selectByCardNo(fromCard).getExpDate().getYear()+"/"+cardRepository.selectByCardNo(fromCard).getExpDate().getMonth()).equals(exp)){
            System.out.println("exp is wrong");
            return -8;
        } else if(cardRepository.selectByCardNo(fromCard).getExpDate().before(date)){
            System.out.println("your card is expired");
            return -9;
        }
        Account account=accountService.selectAccountById(cardRepository.selectByCardNo(fromCard).getAccountId());
        account.setBalance(account.getBalance()-600-amount);
        accountService.updateByAccountNo(accountService.selectAccountById(cardRepository.selectByCardNo(fromCard).getAccountId()).getAccountNo(),account);

        Account account1=accountService.selectAccountById(cardRepository.selectByCardNo(toCard).getAccountId());
        account1.setBalance(account1.getBalance()+amount);
        accountService.updateByAccountNo(accountService.selectAccountById(cardRepository.selectByCardNo(toCard).getAccountId()).getAccountNo(),account1);

        repeat=0;

        transactionService.createNewTransaction(amount,TransType.CardtoCard,cardRepository.selectByCardNo(fromCard).getAccountId(),cardRepository.selectByCardNo(toCard).getAccountId(),user );

        System.out.println("your transaction successfully performed.");
        return 1;
    }

    public int getCardIdByCardNo(String cardNo) throws SQLException {

        return cardRepository.selectByCardNo(cardNo).getId();
    }

    public  void addCardToAccount(String accountNo,String expDate,String pass2) throws SQLException, ParseException {
        if(accountService.selectByAccountNo(accountNo)==null){
            System.out.println("account not found");
            return;
        }
        if (accountService.selectByAccountNo(accountNo).getCardId() <= 0) {
            String cardNo = addCard(accountService.selectByAccountNo(accountNo).getId(), new Date(new SimpleDateFormat("YYYY/MM").parse(expDate).getTime()), pass2);
            accountService.updateByAccountNo(accountNo, new Account(accountNo, accountService.selectByAccountNo(accountNo).getCustomerId(), accountService.selectByAccountNo(accountNo).getBranchId(), accountService.selectByAccountNo(accountNo).getAccountType(), accountService.selectByAccountNo(accountNo).getBalance(), getCardIdByCardNo(cardNo)));
            System.out.println("card added to this account.");


        } else
            System.out.println("before you have card with this account.");


    }

    public Cards selectCardByAccountId(int accountId) throws SQLException {
        if(cardRepository.selectByAccountId(accountId)!=null){
            return cardRepository.selectByAccountId(accountId);
        }
        System.out.println("no card with this account Id");
        return null;
    }

    public Cards selectCardByCardId(int cardId) throws SQLException {
        if(cardRepository.selectByCardId(cardId)!=null){
            return cardRepository.selectByCardId(cardId);
        }
        System.out.println("no card with this account Id");
        return null;
    }

    public void update(String cardNo, Cards cards) throws SQLException {
        cardRepository.updateByCardNo(cardNo,cards);
    }


}
