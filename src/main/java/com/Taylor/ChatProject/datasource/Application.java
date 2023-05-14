package com.Taylor.ChatProject.datasource;

import com.Taylor.ChatProject.datasource.communications.ExternalCommunicationManager;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.presentation.UIHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        application.start();

        Thread thread = new Thread(MessageHandler.getSingleton());
        thread.start();

        ExternalCommunicationManager.getSingleton();
        UIHandler handler = UIHandler.getSingleton();
    }

    public void start() {
        // Initialize any necessary components or perform application startup tasks
        // You can add any initialization logic here

        // Start the UI or other application components
        // You can add the necessary code here to start your application
    }
}
