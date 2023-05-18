package com.Taylor.ChatProject.datasource;

public class ClientInformation {
    private static ClientInformation singleton;

    private String username;

    private String latestRecipient;

    private String serverAddress;

    private ClientInformation(){
        serverAddress = "http://localhost:8080/";
    }

    public static ClientInformation getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new ClientInformation();
        return singleton;
    }

    public String getServerAddress(){
        return serverAddress;
    }

    public void setServerAddress(String serverAddress){
        this.serverAddress = serverAddress;
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
