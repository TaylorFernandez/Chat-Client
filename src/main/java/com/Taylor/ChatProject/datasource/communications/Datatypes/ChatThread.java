package com.Taylor.ChatProject.datasource.communications.Datatypes;

import java.util.List;

public class ChatThread {
    private final String username1;
    private final String username2;

    private final List<ChatMessage> chats;

    public ChatThread(String username1, String username2, List<ChatMessage> chats){
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
}
