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

    public void insert(Transactions transactions) {
        try {
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
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void updateByTransId(int transId, Transactions transactions)  {
        try {
            String updateSql = "update  transactions " +
                    "set amount=?,  transType=?::transType , customerId=?, desCustomerId=?, dateTime=?, operatorId=? where transId=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setLong(1, transactions.getAmount());
            preparedStatement.setString(2, transactions.getTransType().toString());
            preparedStatement.setInt(3, transactions.getAccountId());
            preparedStatement.setInt(4, transactions.getDesAccountId());
            preparedStatement.setTimestamp(5, transactions.getDateTime());
            preparedStatement.setString(6, transactions.getOperator());
            preparedStatement.setInt(7, transId);


            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }



    public void deleteByTransId(String transId)  {
        try {
            String deleteSql = "delete from transactions where  natinalId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, transId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Transactions> searchByDateAndAccountId(int acountId,Timestamp timestamp)  {
        List<Transactions> transactions = new ArrayList<>();

        try {
            String selectSql = "select * from transactions where  dateTime>? and (accountId=? or desAccountId=?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setInt(2, acountId);
            preparedStatement.setInt(3, acountId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transactions transaction = new Transactions();

                transaction.setId(resultSet.getInt("id"));
                transaction.setAmount(resultSet.getLong("amount"));
                transaction.setTransType(TransType.valueOf(resultSet.getString("transType")));
                transaction.setAccountId(resultSet.getInt("accountId"));
                transaction.setDesAccountId(resultSet.getInt("desAccountId"));
                transaction.setDateTime(resultSet.getTimestamp("dateTime"));
                transaction.setOperator(resultSet.getString("operator"));

                transactions.add(transaction);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;

    }


}
