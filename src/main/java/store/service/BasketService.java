package store.service;

import store.entity.CustomerBasket;
import store.entity.Product;
import store.repository.Connect;
import store.repository.CustomerBasketRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class BasketService {
    private Connection connection;
    private CustomerBasketRepository customerBasketRepository;

    public BasketService() {
        this.connection =  Connect.getInstance().getConnect();
        this.customerBasketRepository =new CustomerBasketRepository(connection);
    }

    public void addBasket(String nationalId, int productId, int num){
        ProductService productService=new ProductService();
        UserService userService=new UserService();
        Product product =productService.select(productId);
        Double total =product.getPrice()*num;
        try {
            customerBasketRepository.add(new CustomerBasket(userService.findId(nationalId), productId, num, total));
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}
