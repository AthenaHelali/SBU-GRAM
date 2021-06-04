package main.Client.model;
import main.Client.ToServer;
import main.Common.Account;
import javafx.application.Application;
import javafx.stage.Stage;

public class mainPage extends Application {
    public static Account cerrentAccount;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }


    public static void main(String[] args) {
        boolean connection=ToServer.connectToServer();
        launch(args);
    }
}
