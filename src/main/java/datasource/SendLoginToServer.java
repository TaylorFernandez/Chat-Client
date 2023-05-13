package datasource;

import datasource.report.LoginStatusReport;
import datasource.report.Report;
import datasource.report.ReportHandler;

public class SendLoginToServer implements ServerCommunication{
    private final String username;
    private final String password;
    public SendLoginToServer(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public Report sendToServer() {
        System.out.println("Username: " + username + "\nPassword: " + password);
        ReportHandler.getSingleton().addNewReport(new LoginStatusReport(true, "Login Successful!"));
        return new LoginStatusReport(true, "Login Successful!");
    }
}
