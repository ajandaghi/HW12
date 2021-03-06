package org.bank.repository;

import org.bank.entity.Account;
import org.bank.entity.Customers;
import org.bank.entity.StaffType;
import org.bank.entity.Staffs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffsRepository {
    Connection connection;

    public StaffsRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Staffs staffs) {
        try {
            String insertSql = "insert into staffs (userId, fullName, pass,staffType, branchNo)" +
                    "values(?,?,?,?::staffType,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, staffs.getUser());
            preparedStatement.setString(2, staffs.getFullName());
            preparedStatement.setString(3, staffs.getPass());

            preparedStatement.setString(4, staffs.getStaffType().toString());
            preparedStatement.setString(5, staffs.getBranchNo());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateByStaffUser(String user, Staffs staffs)  {
        try {
            String updateSql = "update  staffs set " +
                    "fullName=?, pass=?, staffType=?::staffType, branchNo=? where  userId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, staffs.getFullName());
            preparedStatement.setString(2, staffs.getPass());
            preparedStatement.setString(3, staffs.getStaffType().toString());
            preparedStatement.setString(4, staffs.getBranchNo());
            preparedStatement.setString(5, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Staffs selectByUser(String  user)  {
        Staffs staffs = null;
        try {
            String selectSql = "select * from staffs where  userId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                staffs = new Staffs();
                staffs.setUser(resultSet.getString("userId"));
                staffs.setPass(resultSet.getString("pass"));
                staffs.setFullName(resultSet.getString("fullName"));
                staffs.setStaffType(StaffType.valueOf(resultSet.getString("staffType")));
                staffs.setBranchNo(resultSet.getString("branchNo"));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return staffs;
    }
    public void deleteByUser(int user)  {
        try {
            String deleteSql = "delete from staffs where  userId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, user);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getLastId() throws SQLException {
        int num = 0;
        try {
            String selectSql = "select count(*) from staffs";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                num = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  num;
    }

    public Staffs selectBranchBoss(String  branchNo)  {
        Staffs staffs = null;
        try {
            String selectSql = "select * from staffs where  branchNo=? and staffType=?::staffType";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, branchNo);
            preparedStatement.setString(2, "Boss");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                staffs = new Staffs();
                staffs.setUser(resultSet.getString("userId"));
                staffs.setPass(resultSet.getString("pass"));
                staffs.setFullName(resultSet.getString("fullName"));
                staffs.setStaffType(StaffType.valueOf(resultSet.getString("staffType")));
                staffs.setBranchNo(resultSet.getString("branchNo"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return staffs;
    }

}
