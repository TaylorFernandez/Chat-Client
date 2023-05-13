import datasource.report.ReportHandler;
import model.MessageHandler;
import presentation.LoginWindow;
import presentation.UIHandler;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(MessageHandler.getSingleton());
        thread.start();

        UIHandler handler = UIHandler.getSingleton();

    }
}