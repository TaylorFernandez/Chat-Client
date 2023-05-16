package com.Taylor.ChatProject.datasource;

public class ClientInformation {
    private static ClientInformation singleton;

    private String username;

    private String latestRecipient;

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

    public void setLatestRecipient(String recipient){
        this.latestRecipient = recipient;
    }

    public String getLatestRecipient(){
        return latestRecipient;
    }
}
