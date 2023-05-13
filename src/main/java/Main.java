import model.MessageHandler;
import presentation.UIHandler;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(MessageHandler.getSingleton());
        thread.start();

        Thread thread2 = new Thread(UIHandler.getSingleton());
        thread2.start();
    }
}