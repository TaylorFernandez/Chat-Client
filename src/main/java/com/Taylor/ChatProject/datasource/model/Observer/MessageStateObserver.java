package com.Taylor.ChatProject.datasource.model.Observer;

import com.Taylor.ChatProject.datasource.model.State.LoginInitialState;
import com.Taylor.ChatProject.datasource.model.State.State;

import java.util.ArrayList;
import java.util.List;

public class MessageStateObserver{

    private static MessageStateObserver observer;

    private final List<Observable> observers = new ArrayList<Observable>();

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


    public void addObserver(Observable obs){
        observers.add(obs);
    }

    public void notifyAllObservers(){
        for(Observable obs : observers){
            obs.update();
        }
    }
}
