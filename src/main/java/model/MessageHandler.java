package model;

import model.Command.Command;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler implements Runnable {

    public static MessageHandler singleton;
    List<Command> commandQueue = new ArrayList<>();


    private MessageHandler(){

    }

    public static MessageHandler getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new MessageHandler();
        System.out.println("MessageHandler: " + singleton.toString());
        return singleton;
    }

    public void QueueCommand(Command c){
        System.out.println("C: " + c.toString());
        commandQueue.add(c);
        System.out.println("Array: " + commandQueue.toString());
        System.out.println("Singleton: " + singleton.toString());
    }

    public void executeNextCommand(){
        Command c = commandQueue.get(0);
        c.execute();
         
    }


    @Override
    public void run() {
        while(true){
            if(commandQueue.size() != 0){
                System.out.println("Queue Length: " + commandQueue.size());
                Command currentCommand = commandQueue.get(0);
                commandQueue.remove(0);

                currentCommand.execute();
            }
        }
    }
}
