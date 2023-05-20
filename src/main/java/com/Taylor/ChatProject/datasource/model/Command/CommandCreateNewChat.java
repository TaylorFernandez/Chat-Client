package com.Taylor.ChatProject.datasource.model.Command;

import com.Taylor.ChatProject.datasource.communications.Repository.ChatRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestCreateNewChat;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.model.report.CreateNewChatReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import java.io.IOException;

public class CommandCreateNewChat implements Command {
    private String username;

    public CommandCreateNewChat(String username){
        this.username = username;
    }
    @Override
    public void execute() throws IOException {
        RequestCreateNewChat request = new RequestCreateNewChat(username);
        BasicResponse response = ChatRepository.getSingleton().createNewChat(request);
        CreateNewChatReport report = new CreateNewChatReport(response.getSuccess(), response.getDescription());
        ReportHandler.getSingleton().addNewReport(report);
    }
}
