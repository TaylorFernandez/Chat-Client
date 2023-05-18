package com.Taylor.ChatProject.datasource.model.Command;

import com.Taylor.ChatProject.datasource.communications.Repository.ChatRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestSendNewChat;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;

import java.io.IOException;

public class CommandSendNewChat implements Command{
    private String sender;
    private String destination;

    private String message;

    private ChatRepository repository;

    public CommandSendNewChat(String sender, String destination, String message){
        this.sender = sender;
        this.destination = destination;
        this.message = message;
        this.repository = ChatRepository.getSingleton();
    }
    @Override
    public void execute() throws IOException {
        RequestSendNewChat request = new RequestSendNewChat(sender, destination, message);
        BasicResponse response = repository.sendNewChat(request);
    }
}
