package org.bank.repository;

import org.bank.entity.Customers;
import org.bank.entity.Gender;
import org.bank.entity.TransType;
import org.bank.entity.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransRepository {
    private Connection connection;

    public TransRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Transactions transactions) throws SQLException {
        String insertSql = "insert into transactions ( transId,amount,transType, customerId, desCustomerId, dateTime, operatorId)" +
                "values(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setInt(1,transactions.getTransId());
        preparedStatement.setLong(2, transactions.getAmount());
        preparedStatement.setString(3, transactions.getTransType().toString());
        preparedStatement.setInt(4, transactions.getCustomerId());
        preparedStatement.setInt(5, transactions.getDesCustomerId());
        preparedStatement.setTimestamp(6, transactions.getDateTime());
        preparedStatement.setInt(7, transactions.getOperatorId());



        preparedStatement.execute();

    }
    public void updateByTransId(int transId, Transactions transactions) throws SQLException {
        String updateSql="update  transactions " +
                "set amount=?,  transType=? , customerId=?, desCustomerId=?, dateTime=?, operatorId=? where transId=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setLong(1, transactions.getAmount());
        preparedStatement.setString(2, transactions.getTransType().toString());
        preparedStatement.setInt(3, transactions.getCustomerId());
        preparedStatement.setInt(4, transactions.getDesCustomerId());
        preparedStatement.setTimestamp(5,transactions.getDateTime());
        preparedStatement.setInt(6,transactions.getOperatorId());
        preparedStatement.setInt(7,transId);


        preparedStatement.executeUpdate();

    }

    public Transactions selectByTransId(String transId) throws SQLException {
        String selectSql = "select * from transactions where  transId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,transId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transactions transactions = null;
        while (resultSet.next()) {
            transactions = new Transactions();

            transactions.setId(resultSet.getInt("id"));
            transactions.setTransId(resultSet.getInt("transId"));
            transactions.setAmount(resultSet.getLong("amount"));
            transactions.setTransType(TransType.valueOf(resultSet.getString("transType")));
            transactions.setCustomerId(resultSet.getInt("customerId"));
            transactions.setDesCustomerId(resultSet.getInt("desCustomerId"));
            transactions.setDateTime(resultSet.getTimestamp("dateTime"));
            transactions.setOperatorId(resultSet.getInt("operatorId"));


       }
        return transactions;
    }

    public void deleteByTransId(String transId) throws SQLException {
        String deleteSql = "delete from transactions where  natinalId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,transId);
        preparedStatement.executeUpdate();
    }
}
