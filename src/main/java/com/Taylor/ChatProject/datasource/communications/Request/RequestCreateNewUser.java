package com.Taylor.ChatProject.datasource.communications.Request;

public class RequestCreateNewUser implements Request{
    private String username;
    private String password;

    public RequestCreateNewUser(String username, String password){
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
