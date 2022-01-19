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
    public void insert(Cards cards) throws SQLException {
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

    }
    public void updateByCardNo(String cardNo, Cards cards) throws SQLException {
        String updateSql="update  cards " +
                "set accountId=?,  cvv2=? , expDate=?, pass2=?, isEnable=? where  cardNo=?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setInt(1, cards.getAccountId());
        preparedStatement.setString(2, cards.getCvv2());
        preparedStatement.setDate(3, cards.getExpDate());
        preparedStatement.setString(4, cards.getPass2());
        preparedStatement.setBoolean(5, cards.getEnable());
        preparedStatement.setString(6, cards.getCardNo());


        preparedStatement.executeUpdate();

    }

    public Cards selectByCarddNo(String cardNo) throws SQLException {
        String selectSql = "select * from cards where  cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Cards card = null;
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
        return card;
    }

    public void deleteByCardNo(String cardNo) throws SQLException {
        String deleteSql = "delete from cards where  cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,cardNo);
        preparedStatement.executeUpdate();
    }

    public int searchCardByAccountId(int accountId) throws SQLException {
        String select ="select count(*) from cards where accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setInt(1,accountId);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }

         return-1;

    }
}
