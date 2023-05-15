package com.Taylor.ChatProject.datasource.model.report;

import java.util.List;

public class GetPeersReport implements Report{
    private List<String> peers;
    private boolean success;
    private String description;

    public GetPeersReport(boolean success, String description,List<String> peers){
        this.success = success;
        this.description = description;
        this.peers = peers;
    }

    public List<String> getPeers(){
        return peers;
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
