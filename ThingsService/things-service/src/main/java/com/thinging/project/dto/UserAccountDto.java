package com.thinging.project.dto;

import com.thinging.project.entity.UserAccount;
import com.thinging.project.utils.Role;

public class UserAccountDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public UserAccount mapToUser(UserAccount user){
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setRole(this.getRole());
        return user;
    }

    public void mapFromUser(UserAccount user){
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setRole(user.getRole());
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
