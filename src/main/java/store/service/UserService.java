package store.service;

import store.entity.User;
import store.entity.enumeration.UserType;
import store.repository.Connect;
import store.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Connection connection;
    private UserRepository userRepository;

    public UserService( ) {
        this.connection =  Connect.getInstance().getConnect();
        this.userRepository = new UserRepository(connection);
    }

    public void add(String fullName, String nationalId, String password, UserType userType){
        User user=new User(fullName,nationalId,password,userType);
        List<User> users=new ArrayList<>();
       try {
           users = userRepository.findAll();


           for (User value : users) {
               if (value.getNationalId().equals(nationalId)) {
                   System.out.println("national id is registered before");
                   return;
               }
           }

           userRepository.add(user);
           System.out.println("user added successfully.");
       } catch (SQLException e){
           e.getStackTrace();
       }

    }

    public int findId(String nationalId){
        try {
            for (User user1 : userRepository.findAll()) {
                if (user1.getNationalId().equals(nationalId))
                    return user1.getId();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

       return 0;
    }

    public User login(String nationalId, String pass){
        try {
            for (User user : userRepository.findAll()) {
                if (user.getNationalId().equals(nationalId) && user.getPassword().equals(pass))
                    return user;
            }
            return null;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
