package presentation;

import datasource.report.ReportHandler;
import model.Observer.Observable;
import model.Observer.Observer;
import model.State.LoginInitialState;
import model.State.LoginWaitingState;
import model.State.MenuInitState;

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
