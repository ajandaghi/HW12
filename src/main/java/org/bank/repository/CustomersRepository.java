package org.bank.repository;

import org.bank.entity.Cards;
import org.bank.entity.Customers;
import org.bank.entity.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersRepository {
    private Connection connection;

    public CustomersRepository(Connection connection) {
        this.connection = connection;
    }
    public void insert(Customers customers) throws SQLException {
        String insertSql = "insert into customers (userId, pass, nationalId, fullName, gender, address, isEnable)" +
                "values(?,?,?,?,?::gender,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, customers.getUser());
        preparedStatement.setString(2, customers.getPass());

        preparedStatement.setString(3, customers.getNationalId());
        preparedStatement.setString(4, customers.getFullName());
        preparedStatement.setString(5, customers.getGender().name());
        preparedStatement.setString(6, customers.getAddress());
        preparedStatement.setBoolean(7, customers.getEnable());



        preparedStatement.execute();

    }
    public void updateByUser(String user, Customers customers) throws SQLException {
        String updateSql="update  customers " +
                "set  fullName=? , gender=?::gender, address=?, nationalId=?, pass=? , isEnable=? where userId=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, customers.getFullName());
        preparedStatement.setString(2, customers.getGender().toString());
        preparedStatement.setString(3, customers.getAddress());
        preparedStatement.setString(4, customers.getNationalId());
        preparedStatement.setString(5, customers.getPass());
        preparedStatement.setBoolean(6, customers.getEnable());

        preparedStatement.setString(7, user);




        preparedStatement.executeUpdate();

    }

    public Customers selectByUser(String user) throws SQLException {
        String selectSql = "select * from customers where  userId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,user);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customers customer = null;
        while (resultSet.next()) {
           customer = new Customers();

            customer.setId(resultSet.getInt("id"));
            customer.setUser(resultSet.getString("userId"));
            customer.setPass(resultSet.getString("pass"));

            customer.setNationalId(resultSet.getString("nationalId"));
            customer.setFullName(resultSet.getString("fullName"));
            customer.setGender(Gender.valueOf(resultSet.getString("gender")));
            customer.setAddress(resultSet.getString("address"));
            customer.setEnable(resultSet.getBoolean("isEnable"));

        }
        return customer;
    }

    public void deleteByUser(String user) throws SQLException {
        String deleteSql = "delete from customers where  userId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,user);
        preparedStatement.executeUpdate();
    }

    public int getLastId() throws SQLException {
        String selectSql = "select count(*) from customers";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int num=0;
        if(resultSet.next()){
            num=resultSet.getInt(1);
        }
        return  num+1;
    }

    public Customers selectByCustomerId(int customerId) throws SQLException {
        String selectSql = "select * from customers where  Id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1,customerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customers customer = null;
        while (resultSet.next()) {
            customer = new Customers();

            customer.setId(resultSet.getInt("id"));
            customer.setUser(resultSet.getString("userId"));
            customer.setPass(resultSet.getString("pass"));

            customer.setNationalId(resultSet.getString("nationalId"));
            customer.setFullName(resultSet.getString("fullName"));
            customer.setGender(Gender.valueOf(resultSet.getString("gender")));
            customer.setAddress(resultSet.getString("address"));
            customer.setEnable(resultSet.getBoolean("isEnable"));


        }
        return customer;
    }
}
