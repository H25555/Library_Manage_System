package org.example.model;

import java.util.List;

public class User {
    private int id;
    private String name;

    private String address;
    private String phonenumber;

    private String password;

    private Role role;
    private UserStatus userStatus;


    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }



    public User(int id, String name, String address, String phonenumber,String password, Role role, UserStatus userStatus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;

    }



    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getName() + "," + this.getAddress() + "," + this.getPhonenumber() + "," + this.getPassword() + "," + this.getRole() + "," + this.getUserStatus() ;
    }
}
