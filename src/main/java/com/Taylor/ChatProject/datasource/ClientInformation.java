package com.Taylor.ChatProject.datasource;

public class ClientInformation {
    private static ClientInformation singleton;

    private String username;

    private ClientInformation(){}

    public static ClientInformation getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new ClientInformation();
        return singleton;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
