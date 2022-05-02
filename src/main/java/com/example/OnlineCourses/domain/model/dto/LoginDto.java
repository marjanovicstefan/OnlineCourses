package com.example.OnlineCourses.domain.model.dto;

public class LoginDto {

    private String userName;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
