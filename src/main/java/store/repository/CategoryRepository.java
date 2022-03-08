package store.repository;

import store.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements interfaceRepository<Category> {
    private Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Category category) throws SQLException {
        String insert="insert into Category (parentId,name) values (?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(insert);
        preparedStatement.setInt(1, category.getParentId());
        preparedStatement.setString(2,category.getName());
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<Category> findAll() throws SQLException {
        String select="select * from Category";
        PreparedStatement preparedStatement=connection.prepareStatement(select);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Category> categories=new ArrayList<>();
        while(resultSet.next()){
            Category category=new Category(resultSet.getInt("id"),resultSet.getInt("parentId"),resultSet.getString("name"));
            categories.add(category);
        }
        return categories;
    }

    @Override
    public int update(Category category) throws SQLException {
        String update= "update category set parentId=?, name=? where id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(update);
        preparedStatement.setInt(1,category.getParentId());
        preparedStatement.setString(2,category.getName());
        preparedStatement.setInt(3,category.getId());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
        String delete="delete from category where id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();

    }
}
