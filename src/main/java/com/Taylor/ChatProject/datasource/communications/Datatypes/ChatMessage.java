package com.Taylor.ChatProject.datasource.communications.Datatypes;

import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatMessage {
     private final String sender;

     private final String message;

     @JsonCreator
     public ChatMessage(@JsonProperty("sender") String sender,
                        @JsonProperty("message") String message){
         this.sender = sender;
         this.message = message;
     }

     public String getSender(){
         return sender;
     }
     public String getMessage(){
         return message;
     }

    public static String getJson(ChatMessage message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(message);
    }
}
