package presentation;

import model.State.LoginInitialState;
import model.State.LoginWaitingState;
import model.StateHandler;
import presentation.login.LoginWindow;

public class UIHandler implements Runnable{

    private static UIHandler singleton;

    private final LoginWindow window;

    private UIHandler(){
        window = new LoginWindow();
    }

    public static synchronized UIHandler getSingleton(){
        if(singleton != null){
            return singleton;
        }
        singleton = new UIHandler();
        return singleton;
    }
    @Override
    public void run() {
        while(true) {
            if (StateHandler.getSingleton().getCurrentState().getClass() == LoginInitialState.class) {
                window.isVisible(true);
            }

            if(StateHandler.getSingleton().getCurrentState().getClass() == LoginWaitingState.class){
                window.isVisible(false);
            }
        }
    }
}
