package store.entity;

import store.entity.enumeration.UserType;

import javax.persistence.Entity;

@Entity
public class Customer extends User{

private String address;
private Long budget;

    public Customer() {

    }

    public Customer(Integer id,  String address, Long budget) {
        super(id);
        this.address = address;
        this.budget = budget;
    }

    public Customer(String fullName, String nationalId, String password, UserType userType, String address, Long budget) {
        super(fullName, nationalId, password, userType);
        this.address = address;
        this.budget = budget;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
}
