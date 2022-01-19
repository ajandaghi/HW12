package org.bank.services;

import org.bank.Connect;
import org.bank.repository.BranchRepository;
import org.bank.repository.StaffsRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeActions  {
    private Connection connection = Connect.getInstance().getConnect();
    private StaffsRepository staffsRepository;
    private BranchRepository branchRepository;



    public EmployeeActions() throws SQLException, ClassNotFoundException {
        staffsRepository=new StaffsRepository(connection);
        branchRepository=new BranchRepository(connection);
    }

    public Boolean login(String user, String pass) throws SQLException {
       if(staffsRepository.selectByUser(user).getUser().equals(user)&&staffsRepository.selectByUser(user).getPass().equals(pass)) {
           return true;
       }
       return false;
    }
}
