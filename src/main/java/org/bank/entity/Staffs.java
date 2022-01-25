package org.bank.entity;

public class Staffs {
    private int id;
    private String user;
    private String fullName;
    private String pass;
    private StaffType staffType;
    private String branchNo;

    public Staffs(String user, String fullName, String pass, StaffType staffType, String branchNo) {
        this.user = user;
        this.fullName = fullName;
        this.pass = pass;
        this.staffType = staffType;
        this.branchNo = branchNo;
    }

    public Staffs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
