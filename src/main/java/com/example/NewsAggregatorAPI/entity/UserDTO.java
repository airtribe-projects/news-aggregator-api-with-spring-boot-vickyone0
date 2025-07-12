package com.example.NewsAggregatorAPI.entity;


public class UserDTO {
    private String username;

    public UserDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    private  String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
