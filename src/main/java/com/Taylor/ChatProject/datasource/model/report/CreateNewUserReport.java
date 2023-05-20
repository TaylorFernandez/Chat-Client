package com.Taylor.ChatProject.datasource.model.report;

public class CreateNewUserReport implements Report{

    private boolean success;
    private String description;

    public CreateNewUserReport(boolean success, String description){
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
