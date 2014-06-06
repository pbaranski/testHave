package com.vhealth.api.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vhealth.api.utils.validators.NewUserName;

@JsonSerialize
public class UserDtoSave {
    @NewUserName(isUpdate = false)
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


}
