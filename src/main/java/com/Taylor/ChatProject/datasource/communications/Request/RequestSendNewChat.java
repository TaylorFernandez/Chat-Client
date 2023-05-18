package com.Taylor.ChatProject.datasource.communications.Request;

public class RequestSendNewChat implements Request{
    private String sender;
    private String destination;

    private String message;

    public RequestSendNewChat(String sender, String destination, String message){
        this.sender = sender;
        this.destination = destination;
        this.message = message;
    }

    public String getSender(){
        return sender;
    }

    public String getDestination(){
        return destination;
    }

    public String getMessage(){
        return message;
    }
}
