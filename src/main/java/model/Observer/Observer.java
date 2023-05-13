package model.Observer;

import java.util.ArrayList;
import java.util.List;

import model.State.LoginInitialState;
import model.State.State;

public class Observer {
    private static Observer observer;

    private final List<Observable> observers = new ArrayList<Observable>();
    private State state;

    private Observer(){
        this.state = new LoginInitialState();
    }

    public static Observer getSingleton(){
        if(observer != null){
            return observer;
        }
        observer = new Observer();
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

    public void addObserver(Observable obs){
        observers.add(obs);
    }

    private void notifyAllObservers(){
        for(Observable obs : observers){
            obs.update();
        }
    }
}
