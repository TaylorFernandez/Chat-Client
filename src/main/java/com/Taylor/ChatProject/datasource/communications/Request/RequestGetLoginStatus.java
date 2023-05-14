package com.Taylor.ChatProject.datasource.communications.Request;

public class RequestGetLoginStatus implements Request {
    private String username;
    private String password;

    public RequestGetLoginStatus(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
