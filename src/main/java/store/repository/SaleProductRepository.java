package store.repository;

import store.entity.SaleProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SaleProductRepository implements interfaceRepository<SaleProduct>{
    private Connection connection;

    public SaleProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(SaleProduct saleProduct) throws SQLException {
        String insert = "insert into saleProduct values(?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(insert);
        preparedStatement.setInt(1,saleProduct.getCustomerId());
        preparedStatement.setInt(2,saleProduct.getProductId());
        preparedStatement.setInt(3,saleProduct.getNumber());
        preparedStatement.setDouble(4,saleProduct.getTotalPrice());
        preparedStatement.setTimestamp(5,saleProduct.getDate());



        return preparedStatement.executeUpdate();
    }

    @Override
    public List<SaleProduct> findAll() throws SQLException {
        String select="select * from CustomerBasket";
        PreparedStatement preparedStatement=connection.prepareStatement(select);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<SaleProduct> saleProducts= new ArrayList<>();
        while (resultSet.next()){
            SaleProduct saleProduct=new SaleProduct(resultSet.getInt("id"),resultSet.getInt("productId"),resultSet.getInt("number"),resultSet.getDouble("totalPrice"),resultSet.getTimestamp("date"));
            saleProducts.add(saleProduct);
        }
        return saleProducts;
    }

    @Override
    public int update(SaleProduct saleProduct) throws SQLException {
        String update= "update saleProduct set productId=?, number=?, totalPrice=?, date=? where customerId=? ";
        PreparedStatement preparedStatement=connection.prepareStatement(update);
        preparedStatement.setInt(1,saleProduct.getCustomerId());
        preparedStatement.setInt(2,saleProduct.getNumber());
        preparedStatement.setDouble(3,saleProduct.getTotalPrice());
        preparedStatement.setTimestamp(4,saleProduct.getDate());
        preparedStatement.setInt(5,saleProduct.getCustomerId());
        return preparedStatement.executeUpdate();    }

    @Override
    public int delete(int id) throws SQLException {
        String delete="delete from saleProduct where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);

        return preparedStatement.executeUpdate();
    }
}
