package datasource;

public class SendLoginToServer implements ServerCommunication{
    private final String username;
    private final String password;
    public SendLoginToServer(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public void sendToServer() {
        System.out.println("Sent to Server");
        System.out.println("Username: " + username + "\nPassword: " + password);
    }
}
