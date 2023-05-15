package com.Taylor.ChatProject.datasource.communications.Response;

import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatThread;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ResponseGetChats {
    private final ChatThread chats;

    private final boolean success;

    private final String description;

    @JsonCreator
    public ResponseGetChats(@JsonProperty("chats") ChatThread chats,
                            @JsonProperty("success") boolean success,
                            @JsonProperty("description") String description)
    {
        this.chats = chats;
        this.success = success;
        this.description = description;

    }

    public ChatThread getChats(){
        return chats;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getDescription(){
        return description;
    }
}
