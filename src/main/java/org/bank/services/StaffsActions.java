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

    public void addBranchBoss(String user,String branchNo ) throws SQLException {
        if(getBranchIdByUser(user)!=branchRepository.selectByBranchNo(branchNo).getId()){
            System.out.println("this user doesn't work at branchNo: "+branchNo);
            return;
        }
        if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchNo(branchNo)!=null) {
            System.out.println("user not exit. at first create a staff");
            return;
        }
        else if(staffsRepository.selectByUser(user)!=null){
            if(staffsRepository.selectBranchBoss(branchNo)!=null) {
                Staffs boss = staffsRepository.selectBranchBoss(branchNo);
                boss.setStaffType(StaffType.Employee);
                staffsRepository.updateByStaffUser(boss.getUser(), boss);
                System.out.println("branch boss changed!");
            }

            Staffs staffs =staffsRepository.selectByUser(user);
            staffs.setStaffType(StaffType.Boss);
            staffsRepository.updateByStaffUser(user,staffs);
            System.out.println("new boss added!");

            Branch nBranch = branchRepository.selectByBranchNo(branchNo);
            nBranch.setBossStaffId(user);
            branchRepository.updateByBranchNo(branchNo, nBranch);


        }
        else
        System.out.println("branch not exist");
}

public void addBranch(String branchNo, String branchName, String address) throws SQLException {
   if(branchRepository.selectByBranchNo(branchNo)==null) {
       branchRepository.insert(new Branch(branchNo, branchName, address));
       System.out.println("branch added");
   }
   else
       System.out.println("This Branch Already Exists!");
}

public void addEmployee(String user, String pass, String fullName, int branchId) throws SQLException, ClassNotFoundException {
    if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchId(branchId)!=null) {
        staffsRepository.insert(new Staffs(user, fullName, pass, StaffType.Employee, branchRepository.selectByBranchId(branchId).getBranchNo() ));
        System.out.println("branch employee added!");
    }
    else
        System.out.println("user not exist or branch before added");
}

    public void addEmployee(String user, String pass, String fullName, String staffUser) throws SQLException, ClassNotFoundException {
        if(staffsRepository.selectByUser(user)==null && branchRepository.selectByBranchId(getBranchIdByUser(staffUser))!=null) {
            staffsRepository.insert(new Staffs(user, fullName, pass, StaffType.Employee,branchRepository.selectByBranchId(getBranchIdByUser(staffUser) ).getBranchNo()));
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
        }
        else
            System.out.println("not such user");

        return  null;


    }

    public int getBranchIdByUser(String user) throws SQLException {
        if(branchRepository.selectByBranchNo(staffsRepository.selectByUser(user).getBranchNo())!=null)
        return branchRepository.selectByBranchNo(staffsRepository.selectByUser(user).getBranchNo()).getId();
        else
        System.out.println("not such user found");
        return -1;

    }


}
