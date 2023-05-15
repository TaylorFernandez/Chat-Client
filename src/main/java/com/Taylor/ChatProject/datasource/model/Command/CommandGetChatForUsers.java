package com.Taylor.ChatProject.datasource.model.Command;

import com.Taylor.ChatProject.datasource.communications.Repository.ChatRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetChats;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetChats;
import com.Taylor.ChatProject.datasource.model.report.ChatForUsersReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import java.io.IOException;

public class CommandGetChatForUsers implements Command{
    private final String username1;
    private final String username2;

    private final ChatRepository repository;

    public CommandGetChatForUsers(String username1, String username2){
        this.username1 = username1;
        this.username2 = username2;
        this.repository = ChatRepository.getSingleton();
    }

    @Override
    public void execute() throws IOException {
        RequestGetChats request = new RequestGetChats(username1, username2);
        ResponseGetChats response = repository.getChatsForUsers(request);
        ReportHandler.getSingleton().addNewReport(new ChatForUsersReport(response.getSuccess(), response.getDescription(), response.getChats()));
        System.out.println("Response from server" + response.getClass());
    }
}
