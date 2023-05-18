package com.Taylor.ChatProject.datasource.model.Observer;

import java.util.ArrayList;
import java.util.List;

import com.Taylor.ChatProject.datasource.model.State.LoginInitialState;
import com.Taylor.ChatProject.datasource.model.State.State;

public class ApplicationStateObserver{
    private static ApplicationStateObserver observer;

    private final List<Observer> observers = new ArrayList<Observer>();
    private State state;

    private ApplicationStateObserver(){
        this.state = new LoginInitialState();
    }

    public static ApplicationStateObserver getSingleton(){
        if(observer != null){
            return observer;
        }
        observer = new ApplicationStateObserver();
        return observer;
    }

    public int getNumObservers(){
        return observers.size();
    }

    public void setState(State state){
        this.state = state;
        System.out.println("State Changed: " + state.toString());
        notifyAllObservers();
    }
    public State getState(){
        return state;
    }

    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void notifyAllObservers(){
        for(Observer obs : observers){
            obs.update();
        }
    }
}
