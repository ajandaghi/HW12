package store.entity;

import store.entity.enumeration.UserType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String nationalId;
    private String password;
    private UserType userType;
    public User(Integer id) {

    }

    public User() {
        this.id = id;

    }

    public User(String fullName, String nationalId, String password, UserType userType) {
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.password = password;
        this.userType = userType;
    }

    public User(Integer id, String fullName, String nationalId, String password, UserType userType) {
        this.id = id;
        this.fullName = fullName;
        this.nationalId = nationalId;
        this.password = password;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
