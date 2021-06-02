package Client;

import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static User CerrentUser;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
