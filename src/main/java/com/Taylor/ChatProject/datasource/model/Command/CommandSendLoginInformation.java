package com.Taylor.ChatProject.datasource.model.Command;

import com.Taylor.ChatProject.datasource.communications.Repository.LoginRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetLoginStatus;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.report.LoginStatusReport;
import com.Taylor.ChatProject.datasource.report.ReportHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.time.Duration;

public class CommandSendLoginInformation implements Command{
    private final String username;
    private final String password;

    private final LoginRepository repository;

    public CommandSendLoginInformation(String username, String password) {
        this.username = username;
        this.password = password;
        this.repository = new LoginRepository(new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10)));
    }

    @Override
    public void execute() throws IOException {
        RequestGetLoginStatus request = new RequestGetLoginStatus(username, password);
        BasicResponse response = repository.validateLoginInformation(request);
        ReportHandler.getSingleton().addNewReport(new LoginStatusReport(response.getSuccess(), response.getDescription()));
        System.out.println(response.toString());
    }
}
