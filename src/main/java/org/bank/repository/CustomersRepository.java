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
        String insertSql = "insert into customers (nationalId, fullName, gender, address)" +
                "values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, customers.getNationalId());
        preparedStatement.setString(2, customers.getFullName());
        preparedStatement.setString(3, customers.getGender().toString());
        preparedStatement.setString(4, customers.getAddress());



        preparedStatement.execute();

    }
    public void updateByNid(String nationalId, Customers customers) throws SQLException {
        String updateSql="update  customers " +
                "set  fullName=? , gender=?, address=? where nationalId=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, customers.getFullName());
        preparedStatement.setString(2, customers.getGender().toString());
        preparedStatement.setString(3, customers.getAddress());
        preparedStatement.setString(4, customers.getNationalId());




        preparedStatement.executeUpdate();

    }

    public Customers selectByNid(String natinalId) throws SQLException {
        String selectSql = "select * from customers where  natinalId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,natinalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customers customer = null;
        while (resultSet.next()) {
           customer = new Customers();

            customer.setId(resultSet.getInt("id"));
            customer.setNationalId(resultSet.getString("nationalId"));
            customer.setFullName(resultSet.getString("fullName"));
            customer.setGender(Gender.valueOf(resultSet.getString("gender")));
            customer.setAddress(resultSet.getString("address"));


        }
        return customer;
    }

    public void deleteByNid(String natinalId) throws SQLException {
        String deleteSql = "delete from customers where  natinalId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,natinalId);
        preparedStatement.executeUpdate();
    }
}
