package com.portfolio.ab.Security.Dto;

import jakarta.validation.constraints.NotBlank;


public class LoginUser {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    
    //Getter and Setter

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
