package presentation;

import datasource.report.ReportHandler;
import model.State.LoginInitialState;
import model.State.LoginWaitingState;
import model.State.MenuInitState;
import model.StateHandler;

public class UIHandler implements Runnable{

    private static UIHandler singleton;

    private final LoginWindow loginWindow;
    private final MainMenuWindow mainMenu;

    private UIHandler(){
        loginWindow = new LoginWindow();
        mainMenu = new MainMenuWindow();
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
                loginWindow.isVisible(true);
            }

            if(StateHandler.getSingleton().getCurrentState().getClass() == LoginWaitingState.class){
                loginWindow.isVisible(false);
                if(ReportHandler.getSingleton().getNextReport().getSuccess()){
                    System.out.println("Successful");
                    StateHandler.getSingleton().setMainMenuInitState();
                }else{
                    LoginWindow.showFailurePopup();
                    StateHandler.getSingleton().setLoginInitializedState();
                }
            }

            if(StateHandler.getSingleton().getCurrentState().getClass() == MenuInitState.class){
                mainMenu.isVisible(true);
            }
        }
    }
}
