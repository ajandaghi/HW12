package org.bank.entity;

public class Branch {
private int id;
private String branchNo;
private String branchName;
private String address;
private String BossStaffId;

    public Branch(String branchId, String branchName, String address) {
        this.branchNo = branchId;
        this.branchName = branchName;
        this.address = address;

    }

    public Branch() {
    }

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

    public String getBossStaffId() {
        return BossStaffId;
    }

    public void setBossStaffId(String bossStaffId) {
        BossStaffId = bossStaffId;
    }
}
