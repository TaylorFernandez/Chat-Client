package com.Taylor.ChatProject.datasource.model.Command;

import java.io.IOException;
import com.Taylor.ChatProject.datasource.communications.Repository.ChatRepository;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetPeers;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetPeers;
import com.Taylor.ChatProject.datasource.model.report.GetPeersReport;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;

public class CommandGetPeers implements Command{

    private final ChatRepository repository;

    public CommandGetPeers(){
        repository = ChatRepository.getSingleton();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Getting Peers!");
        ResponseGetPeers response = repository.getPeers(new RequestGetPeers());
        ReportHandler.getSingleton().addNewReport(new GetPeersReport(response.getSuccess(),
                                                                    response.getDescription(),
                                                                    response.getPeers()));
        System.out.println("Got Peers!");
    }
}
