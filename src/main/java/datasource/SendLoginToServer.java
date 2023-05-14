package datasource;

import datasource.report.LoginStatusReport;
import datasource.report.Report;
import datasource.report.ReportHandler;

import java.io.IOException;

public class SendLoginToServer implements ServerCommunication{
    private final String username;
    private final String password;
    public SendLoginToServer(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public void sendToServer() throws IOException {
        System.out.println("Username: " + username + "\nPassword: " + password);
        ReportHandler.getSingleton().addNewReport(new LoginStatusReport(false, "Login Successful!"));
        ExternalCommunicationManager.getSingleton().sendData("asdf", "http://localhost:8080/api/endpoint");
    }

    public void testServerCommunications(){

    }
}
