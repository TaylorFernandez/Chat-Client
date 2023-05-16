package com.Taylor.ChatProject.datasource.presentation;

import com.Taylor.ChatProject.datasource.model.Observer.Observable;
import com.Taylor.ChatProject.datasource.model.report.ReportHandler;
import com.Taylor.ChatProject.datasource.model.Observer.ApplicationStateObserver;
import com.Taylor.ChatProject.datasource.model.State.LoginInitialState;
import com.Taylor.ChatProject.datasource.model.State.LoginWaitingState;
import com.Taylor.ChatProject.datasource.model.State.MenuInitState;

public class UIHandler extends Observable {

    private static UIHandler singleton;

    private final LoginWindow loginWindow;
    private MainMenuWindow mainMenu;

    private UIHandler(){
        loginWindow = new LoginWindow();

        ApplicationStateObserver.getSingleton().addObserver(this);
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
        if (ApplicationStateObserver.getSingleton().getState().getClass() == LoginInitialState.class) {
            loginWindow.isVisible(true);
        }

        if(ApplicationStateObserver.getSingleton().getState().getClass() == LoginWaitingState.class){
            loginWindow.isVisible(false);

            while(!ReportHandler.getSingleton().hasReports()){}
            System.out.println("LoginWaitingState");

            if(ReportHandler.getSingleton().getNextReport().getSuccess()){
                System.out.println("\n\nSuccessful\n\n");

                ApplicationStateObserver.getSingleton().setState(new MenuInitState());
            }else{
                LoginWindow.showFailurePopup();
                ApplicationStateObserver.getSingleton().setState(new LoginInitialState());
            }
        }

        if(ApplicationStateObserver.getSingleton().getState().getClass() == MenuInitState.class){
            System.out.println("\n\nMenuInitState\n\n");
            mainMenu = new MainMenuWindow();
            mainMenu.isVisible(true);
        }
    }
}
