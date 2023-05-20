package com.Taylor.ChatProject.datasource.model.report;

public class CreateNewChatReport implements Report{
    private boolean success;
    private String description;

    public CreateNewChatReport(boolean success, String description){
        this.success = success;
        this.description = description;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
