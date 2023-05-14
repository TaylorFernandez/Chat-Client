package com.Taylor.ChatProject.datasource.model.State;

public class LoginWaitingState implements State {
    public LoginWaitingState(){

    }

    @Override
    public String toString() {
        return "Waiting for login to finish";
    }
}
