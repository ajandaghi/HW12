package org.bank.repository;

import org.bank.entity.TransType;
import org.bank.entity.Transactions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransRepository {
    private Connection connection;

    public TransRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Transactions transactions) throws SQLException {
        String insertSql = "insert into transactions ( amount,transType, accountId, desAccountId, dateTime, operator)" +
                "values(?,?::transType,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

        preparedStatement.setLong(1, transactions.getAmount());
        preparedStatement.setString(2, transactions.getTransType().toString());
        preparedStatement.setInt(3, transactions.getAccountId());
        preparedStatement.setInt(4, transactions.getDesAccountId());
        preparedStatement.setTimestamp(5, transactions.getDateTime());
        preparedStatement.setString(6, transactions.getOperator());



        preparedStatement.execute();

    }
    public void updateByTransId(int transId, Transactions transactions) throws SQLException {
        String updateSql="update  transactions " +
                "set amount=?,  transType=?::transType , customerId=?, desCustomerId=?, dateTime=?, operatorId=? where transId=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setLong(1, transactions.getAmount());
        preparedStatement.setString(2, transactions.getTransType().toString());
        preparedStatement.setInt(3, transactions.getAccountId());
        preparedStatement.setInt(4, transactions.getDesAccountId());
        preparedStatement.setTimestamp(5,transactions.getDateTime());
        preparedStatement.setString(6,transactions.getOperator());
        preparedStatement.setInt(7,transId);


        preparedStatement.executeUpdate();

    }



    public void deleteByTransId(String transId) throws SQLException {
        String deleteSql = "delete from transactions where  natinalId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,transId);
        preparedStatement.executeUpdate();
    }

    public List<Transactions> searchByDateAndAccountId(int acountId,Timestamp timestamp) throws SQLException {
        String selectSql = "select * from transactions where  dateTime>? and (accountId=? or desAccountId=?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setTimestamp(1,timestamp);
        preparedStatement.setInt(2,acountId);
        preparedStatement.setInt(3,acountId);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transactions> transactions = new ArrayList<>();
        while (resultSet.next()) {
            Transactions transaction = new Transactions();

            transaction.setId(resultSet.getInt("id"));
            transaction.setAmount(resultSet.getLong("amount"));
            transaction.setTransType(TransType.valueOf(resultSet.getString("transType")));
            transaction.setAccountId(resultSet.getInt("accountId"));
            transaction.setDesAccountId(resultSet.getInt("desAccountId"));
            transaction.setDateTime(resultSet.getTimestamp("dateTime"));
            transaction.setOperator(resultSet.getString("operatorId"));

            transactions.add(transaction);
        }
        return transactions;
    }


}
