package com.Taylor.ChatProject.datasource.model.report;

import com.Taylor.ChatProject.datasource.communications.Datatypes.ChatThread;

public class ChatForUsersReport implements Report{
    private boolean success;
    private String description;

    private ChatThread chats;

    public ChatForUsersReport(boolean success, String description, ChatThread chats){
        this.success = success;
        this.description = description;
        this.chats = chats;
    }
    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public ChatThread getChats() {
        return chats;
    }
}
