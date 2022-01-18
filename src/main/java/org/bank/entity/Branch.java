package org.bank.entity;

public class Branch {
private int id;
private String branchNo;
private String branchName;
private String address;
private int BossStaffId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBossStaffId() {
        return BossStaffId;
    }

    public void setBossStaffId(int bossStaffId) {
        BossStaffId = bossStaffId;
    }
}
