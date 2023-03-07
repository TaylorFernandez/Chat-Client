package model;

import model.Command.Command;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    MessageHandler singleton;
    List<Command> commandQueue = new ArrayList<Command>();


    private MessageHandler(){

    }

    public MessageHandler getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new MessageHandler();
        return singleton;
    }

    public void QueueCommand(Command c){
        commandQueue.add(c);
    }

    public void executeNextCommand(){
        Command c = commandQueue.get(0);
        c.execute();
         
    }


}
