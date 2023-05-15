package com.Taylor.ChatProject.datasource;

import com.Taylor.ChatProject.datasource.communications.ExternalCommunicationManager;
import com.Taylor.ChatProject.datasource.model.MessageHandler;
import com.Taylor.ChatProject.datasource.presentation.UIHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Client {
    public static void main(String[] args) {
        Client application = new Client();
        application.start();

        Thread thread = new Thread(MessageHandler.getSingleton());
        thread.start();

        UIHandler handler = UIHandler.getSingleton();
        ClientInformation.getSingleton();
    }

    public void start() {
        // Initialize any necessary components or perform application startup tasks
        // You can add any initialization logic here

        // Start the UI or other application components
        // You can add the necessary code here to start your application
    }
}
