package com.Taylor.ChatProject.datasource.communications.Response;

public class ResponseGetLoginStatus implements Response{
    public boolean success;
    public String description;

    public ResponseGetLoginStatus(boolean success, String description){
        this.success = success;
        this.description = description;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getDescription(){
        return description;
    }
}
