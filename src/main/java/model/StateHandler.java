package model;

import model.State.LoginInitialState;
import model.State.LoginWaitingState;
import model.State.MenuInitState;
import model.State.State;

public class StateHandler{

    private static StateHandler singleton;
    private State currentState;

    private final State loginInitializedState;
    private final State loginLoadingState;

    private final State mainMenuInitState;
    private StateHandler(){
        loginInitializedState = new LoginInitialState();
        loginLoadingState = new LoginWaitingState();
        mainMenuInitState = new MenuInitState();
        this.currentState = loginInitializedState;

    }

    public static StateHandler getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new StateHandler();
        return singleton;
    }

    public void setLoginLoadingState(){
        currentState = loginLoadingState;
    }

    public void setLoginInitializedState(){
        currentState = loginInitializedState;
    }

    public void setMainMenuInitState(){
        currentState = mainMenuInitState;
    }

    public State getCurrentState(){
        return currentState;
    }





}
