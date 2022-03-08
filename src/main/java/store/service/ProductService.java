package store.service;

import store.entity.Product;
import store.repository.Connect;
import store.repository.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductService {
    private Connection connection;
    private ProductRepository productRepository;

    public ProductService() {
        this.connection = Connect.getInstance().getConnect();
        this.productRepository = new ProductRepository(connection);
    }

    public void showAll() {
        try {
            for (Product value : productRepository.findAll()){
                System.out.println(value.toString());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Product select(int id){
        try {
            for(Product product:productRepository.findAll()){
                if(product.getId()==id)
                    return product;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
