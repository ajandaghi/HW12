package org.bank.services;

import org.bank.Connect;
import org.bank.repository.BranchRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class BranchService {
    private Connection connection= Connect.getInstance().getConnect();
    private BranchRepository branchRepository;

    public BranchService() throws SQLException, ClassNotFoundException {
        branchRepository= new BranchRepository(connection);
    }

    public int getBranchId(String branchNo) throws SQLException {
        return branchRepository.selectByBranchNo(branchNo).getId();
    }

    public String getBranchNo(int branchId) throws SQLException {
        return branchRepository.selectByBranchId(branchId).getBranchNo();
    }
}
