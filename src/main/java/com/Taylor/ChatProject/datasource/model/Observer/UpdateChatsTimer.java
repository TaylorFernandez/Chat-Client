package com.Taylor.ChatProject.datasource.model.Observer;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateChatsTimer{
    private Timer timer;

    public void startExecution(){
        timer = new Timer();
        timer.schedule(new updateMessage(), 500, 2000);
    }

    public void stopExecution(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private static class updateMessage extends TimerTask {
        @Override
        public void run() {
            System.out.println("requesting!");
            ChatListObserver.getSingleton().notifyAllObservers();
        }
    }
}
