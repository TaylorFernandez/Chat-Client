package datasource.report;

public class LoginStatusReport implements Report {
    private final boolean success;
    private final String description;

    public LoginStatusReport(boolean success, String description){
        this.success = success;
        this.description = description;
    }

    public String toString(){
        return "Login Status Report: " + success + "Description: " + description;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getDescription(){
        return description;
    }
}
