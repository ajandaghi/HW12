package org.bank.services;

import org.bank.Connect;
import org.bank.entity.Branch;
import org.bank.entity.Customers;
import org.bank.entity.StaffType;
import org.bank.entity.Staffs;
import org.bank.repository.BranchRepository;
import org.bank.repository.StaffsRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class StaffsActions
{
    private Connection connection =Connect.getInstance().getConnect();
    private StaffsRepository staffsRepository;
    private BranchRepository branchRepository;
    private CustomerService customerService;




    public StaffsActions() throws SQLException, ClassNotFoundException {
        staffsRepository=new StaffsRepository(connection);
        branchRepository=new BranchRepository(connection);
        customerService=new CustomerService();
    }

    public void addBranchBoss(String user, String pass, String fullName, String branchNo ) throws SQLException {
        if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchNo(branchNo)!=null) {
            staffsRepository.insert(new Staffs(user, fullName, pass, StaffType.Boss, branchNo));
            Branch nBranch = branchRepository.selectByBranchNo(branchNo);
            nBranch.setBossStaffId(user);
            branchRepository.updateByBranchNo(branchNo, nBranch);
            System.out.println("branch boss added!");
        }
        else if(staffsRepository.selectByUser(user)!=null){
           Staffs staffs= staffsRepository.selectByUser(staffsRepository.selectBranchBoss(branchNo).getUser());
           staffs.setStaffType(StaffType.Employee);
           staffsRepository.updateByStaffUser(user,staffs);

            staffsRepository.updateByStaffUser(user,new Staffs(user, fullName, pass, StaffType.Boss, branchNo));
            Branch nBranch = branchRepository.selectByBranchNo(branchNo);
            nBranch.setBossStaffId(user);
            branchRepository.updateByBranchNo(branchNo, nBranch);
            System.out.println("branch boss changed!");


        }
            else
        System.out.println("branch not exist");
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
        System.out.println("user not exist or branch before added");
}



    public Boolean adminLogin(String user, String pass) {
        if (user.equals("admin")&& pass.equals("admin")){
            return true;
        }
        return false;
    }

    public StaffType login(String user, String pass) throws SQLException {
        if(staffsRepository.selectByUser(user)!=null){
            if(staffsRepository.selectByUser(user).getUser().equals(user)&& staffsRepository.selectByUser(user).getPass().equals(pass)){
                return staffsRepository.selectByUser(user).getStaffType();
            }
            else
                System.out.println("wrong credential");
        }
        else
            System.out.println("not such user");

        return null;
    }

    public int getBranchIdByUser(String user) throws SQLException {
        return staffsRepository.selectByUser(user).getId();
    }


}
