package org.bank.entity;

public enum AccountType {
    Current(102),
    Saving(100),
    LongInvestment(103),
    ShortInvestment(104);
    private int type;

    AccountType (int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
