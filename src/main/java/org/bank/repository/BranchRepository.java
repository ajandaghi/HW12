package org.bank.repository;

import org.bank.entity.Account;
import org.bank.entity.AccountType;
import org.bank.entity.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchRepository {
    private Connection connection;

    public BranchRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Branch branch) throws SQLException {
        String insertSql = "insert into branch (branchNo, branchName, address)" +
                "values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, branch.getBranchNo());
        preparedStatement.setString(2, branch.getBranchName());
        preparedStatement.setString(3, branch.getAddress());

        preparedStatement.execute();

    }
    public void updateByBranchNo(String branchNo, Branch branch) throws SQLException {
        String updateSql="update  branch " +
                "set branchName=?,  address=? where  branchNo=?" ;
        PreparedStatement preparedStatement= connection.prepareStatement(updateSql);

        preparedStatement.setString(1,branch.getBranchName());
        preparedStatement.setString(2,branch.getAddress());
        preparedStatement.setString(3,branch.getBranchNo());

        preparedStatement.executeUpdate();

    }

    public Branch selectByBranchNo(String branchNo) throws SQLException {
        String selectSql = "select * from branch where  branchNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Branch branch = null;
        while (resultSet.next()) {
             branch = new Branch();

            branch.setBranchNo(resultSet.getString("branchNo"));
            branch.setBranchName(resultSet.getString("branchName"));
            branch.setAddress(resultSet.getString("address"));



        }
        return branch;
    }

    public void deleteByBranchNo(String branchNo) throws SQLException {
        String deleteSql = "delete from branch where  branchNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
        preparedStatement.setString(1,branchNo);
        preparedStatement.executeUpdate();
    }
}
