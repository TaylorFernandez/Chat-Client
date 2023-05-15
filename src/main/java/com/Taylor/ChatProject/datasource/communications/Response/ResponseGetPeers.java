package com.Taylor.ChatProject.datasource.communications.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseGetPeers implements Response{
    private boolean success;
    private String description;
    private List<String> peers;

    @JsonCreator
    public ResponseGetPeers(@JsonProperty("success") boolean success,
                            @JsonProperty("description") String description,
                            @JsonProperty("peers") List<String> peers){
        this.success = success;
        this.description = description;
        this.peers = peers;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getDescription(){
        return description;
    }

    public List<String> getPeers(){
        return peers;
    }
}
