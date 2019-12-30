package com.thinging.project.model.dto;

import com.thinging.project.model.UserAccount;
import com.thinging.project.util.Role;

public class UserAccountResDto {

    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    public UserAccountResDto(UserAccount user){
        this.setEmail(user.getEmail());
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
