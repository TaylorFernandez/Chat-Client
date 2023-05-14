package com.Taylor.ChatProject.datasource.presentation;

import com.Taylor.ChatProject.datasource.report.ReportHandler;
import com.Taylor.ChatProject.datasource.model.Observer.Observable;
import com.Taylor.ChatProject.datasource.model.Observer.Observer;
import com.Taylor.ChatProject.datasource.model.State.LoginInitialState;
import com.Taylor.ChatProject.datasource.model.State.LoginWaitingState;
import com.Taylor.ChatProject.datasource.model.State.MenuInitState;

public class UIHandler extends Observable{

    private static UIHandler singleton;

    private final LoginWindow loginWindow;
    private final MainMenuWindow mainMenu;

    private UIHandler(){
        loginWindow = new LoginWindow();
        mainMenu = new MainMenuWindow();
        Observer.getSingleton().addObserver(this);
        System.out.println(Observer.getSingleton().getNumObservers());
    }

    public static synchronized UIHandler getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new UIHandler();
        return singleton;
    }


    @Override
    public void update() {
        if (Observer.getSingleton().getState().getClass() == LoginInitialState.class) {
            loginWindow.isVisible(true);
        }

        if(Observer.getSingleton().getState().getClass() == LoginWaitingState.class){
            loginWindow.isVisible(false);
            if(ReportHandler.getSingleton().getNextReport().getSuccess()){
                System.out.println("Successful");
                Observer.getSingleton().setState(new MenuInitState());
            }else{
                LoginWindow.showFailurePopup();
                Observer.getSingleton().setState(new LoginInitialState());
            }
        }

        if(Observer.getSingleton().getState().getClass() == MenuInitState.class){
            mainMenu.isVisible(true);
        }
    }
}
