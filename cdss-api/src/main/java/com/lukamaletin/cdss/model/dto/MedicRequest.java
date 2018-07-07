package com.lukamaletin.cdss.model.dto;

import com.lukamaletin.cdss.model.Role;
import com.lukamaletin.cdss.model.User;

import javax.validation.constraints.NotEmpty;

public class MedicRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public MedicRequest() {
    }

    public User newUser() {
        return new User(firstName, lastName, username, password, Role.MEDIC);
    }

    public User updatedUser(User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);

        return user;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
