package com.Taylor.ChatProject.datasource.communications.Request;

import com.Taylor.ChatProject.datasource.ClientInformation;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetPeers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestGetPeers implements Request{
    private String username;

    public RequestGetPeers(){
        username = ClientInformation.getSingleton().getUsername();
    }

    public String getUsername(){
        return username;
    }

    public static String getJson(RequestGetPeers response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);
    }
}
