package main.Client.Controller;

import main.Client.model.PageLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpController {
    public void ToLogin(MouseEvent mouseEvent)throws IOException{
        new PageLoader().load("Login");

    }
}
