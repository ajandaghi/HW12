package store.repository;

import store.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements interfaceRepository<Customer>{
    private Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Customer customer) throws SQLException {
        String insert="insert into Customer (userId, address, budget) values (?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(insert);
        preparedStatement.setInt(1,customer.getId());
        preparedStatement.setString(2,customer.getAddress());
        preparedStatement.setLong(3,customer.getBudget());

        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        String select="select * from Customer";
        PreparedStatement preparedStatement=connection.prepareStatement(select);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Customer> customers=new ArrayList<>();
        while(resultSet.next()){
            Customer customer=new Customer(resultSet.getInt("userId"),resultSet.getString("address"),resultSet.getLong("budget"));
            customers.add(customer);
        }
          return customers;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        String update="update Customer set userId=?, address=?, budget=? where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(update);
        preparedStatement.setInt(1,customer.getId());
        preparedStatement.setString(2,customer.getAddress());
        preparedStatement.setLong(3,customer.getBudget());
        preparedStatement.setInt(4,customer.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
        String delete="delete from Customer where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);
        return  preparedStatement.executeUpdate();
    }
}
