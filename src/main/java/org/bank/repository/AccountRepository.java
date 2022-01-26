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

    public void insert(Account account)  {
        try {
            String insertSql = "insert into account (accountNo, customerId, branchId, accountType, balance, cardId)" +
                    "values(?,?,?,?::accountType,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, account.getAccountNo());
            preparedStatement.setInt(2, account.getCustomerId());
            preparedStatement.setInt(3, account.getBranchId());
            preparedStatement.setString(4, account.getAccountType().toString());
            preparedStatement.setLong(5, account.getBalance());
            preparedStatement.setInt(6, account.getCardId());
            preparedStatement.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void updateByAccountNo(String accountNo, Account account)  {
        try {
            String updateSql = "update  account set customerId=?, " +
                    " branchId=?,  accountType=?::accountType,  balance=?,  cardId=?   where  accountNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, accountNo);

            preparedStatement.setInt(1, account.getCustomerId());
            preparedStatement.setInt(2, account.getBranchId());
            preparedStatement.setString(3, account.getAccountType().toString());
            preparedStatement.setLong(4, account.getBalance());
            preparedStatement.setInt(5, account.getCardId());
            preparedStatement.setString(6, account.getAccountNo());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public Account selectByAccountNo(String accountNo)  {
        Account account = null;

        try {
            String selectSql = "select * from account where accountNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, accountNo);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setAccountNo(resultSet.getString("AccountNo"));
                account.setCustomerId(resultSet.getInt("customerId"));
                account.setBranchId(resultSet.getInt("branchId"));
                account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
                account.setBalance(resultSet.getLong("balance"));
                account.setCardId(resultSet.getInt("cardId"));

            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    public List<Account> select()   {
        List<Account> accounts = new ArrayList<>();
        try {
            String selectSql = "select * from account";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setAccountNo(resultSet.getString("AccountNo"));
                account.setCustomerId(resultSet.getInt("customerId"));
                account.setBranchId(resultSet.getInt("branchId"));
                account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
                account.setBalance(resultSet.getLong("balance"));
                account.setCardId(resultSet.getInt("cardId"));
                accounts.add(account);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    public void deleteByAccountNo(String accountNo)  {
        try {
            String deleteSql = "delete from account where  accountNo=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, accountNo);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public List<Account> selectByCustomerId(int customerId) {
        List<Account> accounts = new ArrayList<>();

        try {
            String selectSql = "select * from account where  CustomerId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setAccountNo(resultSet.getString("AccountNo"));
                account.setCustomerId(resultSet.getInt("customerId"));
                account.setBranchId(resultSet.getInt("branchId"));
                account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
                account.setBalance(resultSet.getLong("balance"));
                account.setCardId(resultSet.getInt("cardId"));
                accounts.add(account);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }
}
