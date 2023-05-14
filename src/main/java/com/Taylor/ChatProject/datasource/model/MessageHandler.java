package com.Taylor.ChatProject.datasource.model;

import com.Taylor.ChatProject.datasource.model.Command.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler implements Runnable {

    private static MessageHandler singleton;
    private final List<Command> commandQueue = new ArrayList<>();

    private MessageHandler() {
    }

    public static synchronized MessageHandler getSingleton() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new MessageHandler();
        return singleton;
    }

    public void queueCommand(Command c) {
        synchronized (commandQueue) {
            commandQueue.add(c);
            commandQueue.notify();
        }
    }

    public void executeNextCommand() throws IOException {
        synchronized (commandQueue) {
            if (!commandQueue.isEmpty()) {
                Command c = commandQueue.remove(0);
                c.execute();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (commandQueue) {
                while (commandQueue.isEmpty()) {
                    try {
                        commandQueue.wait(); // Wait until a new command is added
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    executeNextCommand();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
