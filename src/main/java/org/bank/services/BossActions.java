package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Branch;
import org.bank.entity.StaffType;
import org.bank.entity.Staffs;
import org.bank.repository.BranchRepository;
import org.bank.repository.StaffsRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class BossActions
{
    private Connection connection =Connect.getInstance().getConnect();
    private StaffsRepository staffsRepository;
    private BranchRepository branchRepository;



    public BossActions() throws SQLException, ClassNotFoundException {
        staffsRepository=new StaffsRepository(connection);
        branchRepository=new BranchRepository(connection);
    }

    public void addBranchBoss(String user, String pass, String fullName, String branchNo ) throws SQLException {
        if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchNo(branchNo)!=null) {
            staffsRepository.insert(new Staffs(user, fullName, pass, StaffType.Boss, branchNo));
            Branch nBranch = branchRepository.selectByBranchNo(branchNo);
            nBranch.setBossStaffId(user);
            branchRepository.updateByBranchNo(branchNo, nBranch);
            System.out.println("branch boss added!");
        }
        else
        System.out.println("branch not exist or user is in-Use");
}

public void addBranch(String branchNo, String branchName, String address) throws SQLException {
   if(branchRepository.selectByBranchNo(branchNo)==null)
       branchRepository.insert(new Branch(branchNo,branchName,address));
   else
       System.out.println("This Branch Already Exists!");
}

public void addEmployee(String user, String pass, String fullName, String branchNo) throws SQLException {
    if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchNo(branchNo)!=null) {
        staffsRepository.insert(new Staffs(user, fullName, pass, StaffType.Employee, branchNo));
        System.out.println("branch employee added!");
    }
    else
        System.out.println("branch not exist or user is in-Use");
}

    public Boolean login(String user, String pass) throws SQLException {
        if(staffsRepository.selectByUser(user)!=null ) {
            if (staffsRepository.selectByUser(user).getUser().equals(user) && staffsRepository.selectByUser(user).getPass().equals(pass)) {
                return true;
            }
        }
        return false;
    }


}
