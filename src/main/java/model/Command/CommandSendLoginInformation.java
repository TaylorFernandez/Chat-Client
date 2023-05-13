package model.Command;

import datasource.SendLoginToServer;
import datasource.report.Report;

public class CommandSendLoginInformation implements Command{
    private final String username;
    private final String password;

    public CommandSendLoginInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        SendLoginToServer serverCommunications = new SendLoginToServer(username, password);
        serverCommunications.sendToServer();
    }
}
