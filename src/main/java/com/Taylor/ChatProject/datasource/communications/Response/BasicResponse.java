package com.Taylor.ChatProject.datasource.communications.Response;

import org.apache.catalina.connector.Response;

public class BasicResponse extends Response {
    public boolean success;
    public String description;

    public BasicResponse(boolean success, String description){
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
