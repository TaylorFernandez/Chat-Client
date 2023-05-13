import model.MessageHandler;
import presentation.LoginWindow;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(MessageHandler.getSingleton());
        thread.start();
        LoginWindow window = new LoginWindow();

    }
}