package model.Command;

import datasource.SendLoginToServer;

public class CommandSendLoginInformation implements Command{
    private final String username;
    private final String password;

    public CommandSendLoginInformation(String username, String password) {

        this.username = username;
        this.password = password;
    }
    @Override
    public void execute() {
        System.out.println("Executed Command");
        SendLoginToServer serverCommunications = new SendLoginToServer(username, password);
        serverCommunications.sendToServer();
    }
}
