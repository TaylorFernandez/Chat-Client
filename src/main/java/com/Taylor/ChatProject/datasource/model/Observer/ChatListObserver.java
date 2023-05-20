package com.Taylor.ChatProject.datasource.model.Observer;

import java.util.ArrayList;
import java.util.List;

public class ChatListObserver{
    private static ChatListObserver observer;

    private final List<Observer> observers = new ArrayList<Observer>();

    public static ChatListObserver getSingleton(){
        if(observer != null){
            return observer;
        }
        observer = new ChatListObserver();
        return observer;
    }

    public int getNumObservers(){
        return observers.size();
    }


    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void notifyAllObservers(){
        for(Observer obs : observers){
            System.out.println("Chat List Observer added: " + obs.getClass() + " Num Observers: " + getNumObservers());
            obs.update();
        }
    }
}
