package com.Taylor.ChatProject.datasource.model.Observer;

import java.util.ArrayList;
import java.util.List;

public class MessageStateObserver{

    private static MessageStateObserver observer;

    private final List<TimerObserver> observers = new ArrayList<TimerObserver>();

    public static MessageStateObserver getSingleton(){
        if(observer != null){
            return observer;
        }
        observer = new MessageStateObserver();
        return observer;
    }

    public int getNumObservers(){
        return observers.size();
    }


    public void addObserver(TimerObserver obs){
        observers.add(obs);
    }

    public void notifyAllObservers(){
        for(TimerObserver obs : observers){
            System.out.println("Message State Observer added: " + obs.getClass());
            obs.update();
        }
    }
}
