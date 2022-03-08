package store.repository;

import store.entity.CustomerBasket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBasketRepository implements interfaceRepository<CustomerBasket> {
    private Connection connection;

    public CustomerBasketRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(CustomerBasket customerBasket) throws SQLException {
        String insert = "insert into customerBasket values(?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(insert);
        preparedStatement.setInt(1,customerBasket.getCustomerId());
        preparedStatement.setInt(2,customerBasket.getProductId());
        preparedStatement.setInt(3,customerBasket.getNumber());
        preparedStatement.setDouble(4,customerBasket.getTotalPrice());

        return preparedStatement.executeUpdate();
    }

    @Override
    public List<CustomerBasket> findAll() throws SQLException {
        String select="select * from CustomerBasket";
        PreparedStatement preparedStatement=connection.prepareStatement(select);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<CustomerBasket> customerBaskets= new ArrayList<>();
        while (resultSet.next()){
            CustomerBasket customerBasket=new CustomerBasket(resultSet.getInt("id"),resultSet.getInt("productId"),resultSet.getInt("number"),resultSet.getDouble("totalPrice"));
            customerBaskets.add(customerBasket);
        }
        return customerBaskets;
    }

    @Override
    public int update(CustomerBasket customerBasket) throws SQLException {
        String update= "update customerBasket set productId=?, number=?, totalPrice=? where customerId=? ";
        PreparedStatement preparedStatement=connection.prepareStatement(update);
        preparedStatement.setInt(1,customerBasket.getCustomerId());
        preparedStatement.setInt(2,customerBasket.getNumber());
        preparedStatement.setDouble(3,customerBasket.getTotalPrice());
        preparedStatement.setInt(4,customerBasket.getCustomerId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
        String delete="delete from customerBasket where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate();
    }
}
