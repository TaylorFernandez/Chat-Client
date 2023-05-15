package com.Taylor.ChatProject.datasource.communications.Datatypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ChatThread {
    private final String username1;
    private final String username2;

    private final List<ChatMessage> chats;

    @JsonCreator
    public ChatThread(@JsonProperty("username1") String username1,
                      @JsonProperty("username2") String username2,
                      @JsonProperty("chats") List<ChatMessage> chats){
        this.username1 = username1;
        this.username2 = username2;
        this.chats = chats;
    }

    public String getUsername1(){
        return username1;
    }

    public String getUsername2(){
        return username2;
    }

    public List<ChatMessage> getChats(){
        return chats;
    }

    public static String getJson(ChatThread thread) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(thread);
    }
}
