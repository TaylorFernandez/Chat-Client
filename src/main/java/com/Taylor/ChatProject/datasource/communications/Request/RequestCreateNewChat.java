package com.Taylor.ChatProject.datasource.communications.Request;

import com.Taylor.ChatProject.datasource.ClientInformation;

public class RequestCreateNewChat implements Request{
    private String username;
    private String clientName;

    public RequestCreateNewChat(String username){
        this.username = username;
        this.clientName = ClientInformation.getSingleton().getUsername();
    }

    public String getUsername(){
        return username;
    }

    public String getClientName(){return clientName;}

}
