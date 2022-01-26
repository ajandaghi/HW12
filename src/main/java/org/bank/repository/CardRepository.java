package org.bank.repository;

import org.bank.entity.Branch;
import org.bank.entity.Cards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private Connection connection;

    public CardRepository(Connection connection) {
        this.connection = connection;
    }
    public void insert(Cards cards)  {
        try {
            String insertSql = "insert into cards (accountId, cardNo, cvv2, expDate, pass2, isEnable)" +
                    "values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, cards.getAccountId());
            preparedStatement.setString(2, cards.getCardNo());
            preparedStatement.setString(3, cards.getCvv2());
            preparedStatement.setDate(4, cards.getExpDate());
            preparedStatement.setString(5, cards.getPass2());
            preparedStatement.setBoolean(6, cards.getEnable());


            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void updateByCardNo(String cardNo, Cards cards)  {
        try {
            String updateSql = "update  cards " +
                    "set accountId=?,  cvv2=? , expDate=?, pass2=?, isEnable=? where  cardNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, cards.getAccountId());
            preparedStatement.setString(2, cards.getCvv2());
            preparedStatement.setDate(3, cards.getExpDate());
            preparedStatement.setString(4, cards.getPass2());
            preparedStatement.setBoolean(5, cards.getEnable());
            preparedStatement.setString(6, cardNo);


            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Cards selectByCardNo(String cardNo)  {
        Cards card = null;

        try {
            String selectSql = "select * from cards where  cardNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, cardNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                card = new Cards();

                card.setId(resultSet.getInt("id"));
                card.setCardNo(resultSet.getString("cardNo"));
                card.setAccountId(resultSet.getInt("accountId"));
                card.setCvv2(resultSet.getString("cvv2"));
                card.setExpDate(resultSet.getDate("expDate"));
                card.setPass2(resultSet.getString("pass2"));
                card.setEnable(resultSet.getBoolean("isEnable"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return card;
    }

    public void deleteByCardNo(String cardNo)  {
        try {

            String deleteSql = "delete from cards where  cardNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, cardNo);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int searchCardByAccountId(int accountId) {
        try {
            String select = "select count(*) from cards where accountId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);

            }
        }
       catch (SQLException e){
            e.printStackTrace();
        }
         return-1;

    }

    public Cards selectByAccountId(int accountId)  {

        Cards card = null;
        try {
            String selectSql = "select * from cards where  accountId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                card = new Cards();

                card.setId(resultSet.getInt("id"));
                card.setCardNo(resultSet.getString("cardNo"));
                card.setAccountId(resultSet.getInt("accountId"));
                card.setCvv2(resultSet.getString("cvv2"));
                card.setExpDate(resultSet.getDate("expDate"));
                card.setPass2(resultSet.getString("pass2"));
                card.setEnable(resultSet.getBoolean("isEnable"));

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return card;
    }

    public Cards selectByCardId(int cardId)  {
        Cards card = null;
        try {
            String selectSql = "select * from cards where  Id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                card = new Cards();

                card.setId(resultSet.getInt("id"));
                card.setCardNo(resultSet.getString("cardNo"));
                card.setAccountId(resultSet.getInt("accountId"));
                card.setCvv2(resultSet.getString("cvv2"));
                card.setExpDate(resultSet.getDate("expDate"));
                card.setPass2(resultSet.getString("pass2"));
                card.setEnable(resultSet.getBoolean("isEnable"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return card;
    }
}
