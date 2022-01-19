package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Cards;
import org.bank.repository.CardRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

public class CardService {
    CardRepository cardRepository;
    Connection connection= Connect.getInstance().getConnect();

    public CardService() throws SQLException, ClassNotFoundException {
        cardRepository=new CardRepository(connection);
    }

    public void addCard(int accountId, Date expDate,String pass2) throws SQLException {

        Random random = new Random();

        if(cardRepository.searchCardByAccountId(accountId)!=0){
            System.out.println("before you have card with this account.");
            return;
        }

        int i=1;
        String cardNo= (random.nextInt(8999)+1000)+"-"+(random.nextInt(8999)+1000)+"-"+(random.nextInt(8999)+1000)+"-"+ (random.nextInt(899)+100);;

        while(cardRepository.selectByCarddNo(cardNo+i)!=null){
            i++;

        }
        System.out.println((cardNo + i).length());
        cardRepository.insert(new Cards(accountId,cardNo+i,String.valueOf(random.nextInt(899)+100),expDate,pass2,true));
    }
}
