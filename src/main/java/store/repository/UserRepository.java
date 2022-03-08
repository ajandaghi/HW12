package store.repository;

import store.entity.User;
import store.entity.enumeration.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements interfaceRepository<User>{
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(User user) throws SQLException {
        String insert="insert into user_profile (fullName,nationalId,password,userType)" +
                "values(?,?,?,?::UserType)";
        PreparedStatement preparedStatement=connection.prepareStatement(insert);
        preparedStatement.setString(1,user.getFullName());
        preparedStatement.setString(2,user.getNationalId());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setString(4,user.getUserType().toString());

        return preparedStatement.executeUpdate();
    }

    @Override
    public List<User> findAll() throws SQLException {
        String select="select * from user_profile";
        PreparedStatement preparedStatement=connection.prepareStatement(select);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<User> users=new ArrayList<>();
        while(resultSet.next()){
            User user=new User(resultSet.getInt("id"),resultSet.getString("fullName"),resultSet.getString("nationalId"),resultSet.getString("password"), UserType.valueOf(resultSet.getString("UserType")));
            users.add(user);
        }
        return users;
    }

    @Override
    public int update(User user) throws SQLException {
        String update="update user_profile set fullName=?, nationalId=?, password=?, UserType=?::UserType where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(update);
        preparedStatement.setString(1,user.getFullName());
        preparedStatement.setString(2,user.getNationalId());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setString(4,user.getUserType().toString());
        preparedStatement.setInt(5,user.getId());
      return  preparedStatement.executeUpdate();

    }

    @Override
    public int delete(int id) throws SQLException {
        String delete="delete from user_profile where id=? ";
        PreparedStatement preparedStatement=connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
}
