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

    public void insert(Staffs staffs) throws SQLException {
        String insertSql = "insert into staffs (staffId, staffType, branchNo)" +
                "values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setInt(1, staffs.getStaffId());
        preparedStatement.setString(2, staffs.getStaffType().toString());
        preparedStatement.setString(3, staffs.getBranchNo());
        preparedStatement.execute();
    }

    public void updateByStaffId(int staffId, Staffs staffs) throws SQLException {
        String updateSql = "update  staffs set " +
                "staffType=?, branchNo=? where  staffId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, staffs.getStaffType().toString());
        preparedStatement.setString(2, staffs.getBranchNo());
        preparedStatement.setInt(3, staffs.getStaffId());
        preparedStatement.executeUpdate();

    }

    public Staffs selectByStaffId(int staffId) throws SQLException {
        String selectSql = "select * from staffs where  staffId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Staffs staffs = null;
        while (resultSet.next()) {
            staffs = new Staffs();
            staffs.setStaffId(resultSet.getInt("staffId"));
            staffs.setStaffType(StaffType.valueOf(resultSet.getString("staffType")));
            staffs.setBranchNo(resultSet.getString("branchNo"));

        }
        return staffs;
    }
    public void deleteByStaffId(int staffId) throws SQLException {
        String deleteSql = "delete from staffs where  staffId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setInt(1,staffId);
        preparedStatement.executeUpdate();
    }
}
