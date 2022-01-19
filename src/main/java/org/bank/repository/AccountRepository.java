package org.bank.repository;

import org.bank.entity.Account;
import org.bank.entity.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountRepository {
    private Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Account account) throws SQLException {
        String insertSql = "insert into account (accountNo, customerId, branchId, accountType, balance, cardId, isEnable)" +
                "values(?,?,?,?::accountType,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, account.getAccountNo());
        preparedStatement.setInt(2, account.getCustomerId());
        preparedStatement.setInt(3, account.getBranchId());
        preparedStatement.setString(4, account.getAccountType().toString());
        preparedStatement.setLong(5, account.getBalance());
        preparedStatement.setInt(6, account.getCardId());
        preparedStatement.setBoolean(7, account.isEnable());
        preparedStatement.execute();

    }
    public void updateByAccountNo(String accountNo, Account account) throws SQLException {
        String updateSql="update  account set customerId=?, " +
                " branchId=?,  accountType=?::accountType,  balance=?,  cardId=?,  isEnable=? where  accountNo=?" ;
        PreparedStatement preparedStatement= connection.prepareStatement(updateSql);

        preparedStatement.setInt(1,account.getCustomerId());
        preparedStatement.setInt(2,account.getBranchId());
        preparedStatement.setString(3,account.getAccountType().toString());
        preparedStatement.setLong(4,account.getBalance());
        preparedStatement.setInt(5,account.getCardId());
        preparedStatement.setBoolean(6,account.isEnable());
        preparedStatement.setString(7,account.getAccountNo());
        preparedStatement.executeUpdate();

    }

    public Account selectByAccountNo(String accountNo) throws SQLException {
        String selectSql = "select * from account where  accountNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,accountNo);

        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = null;
        while (resultSet.next()) {
           account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setAccountNo(resultSet.getString("AccountNo"));
            account.setCustomerId(resultSet.getInt("customerId"));
            account.setBranchId(resultSet.getInt("branchId"));
            account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
            account.setBalance(resultSet.getLong("balance"));
            account.setCardId(resultSet.getInt("cardId"));
            account.setEnable(resultSet.getBoolean("isEnable"));

        }
        return account;
    }

    public void deleteByAccountNo(String accountNo) throws SQLException {
        String deleteSql = "delete from account where  accountNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,accountNo);
        preparedStatement.executeUpdate();
    }
}
