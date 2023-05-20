package com.Taylor.ChatProject.datasource.model.Command;

import com.Taylor.ChatProject.datasource.communications.Repository.LoginRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestCreateNewUser;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.model.report.CreateNewUserReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

import java.io.IOException;

public class CommandCreateNewUser implements Command{
    private String username;
    private String password;

    private LoginRepository repository;

    public CommandCreateNewUser(String username, String password){
        this.username = username;
        this.password = password;
        repository = LoginRepository.getSingleton();
    }
    @Override
    public void execute() throws IOException {
        RequestCreateNewUser request = new RequestCreateNewUser(username, password);
        BasicResponse response = repository.createNewUser(request);
        CreateNewUserReport report = new CreateNewUserReport(response.getSuccess(), response.getDescription());
        ReportHandler.getSingleton().addNewReport(report);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
