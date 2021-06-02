package Client.Controller;

import Client.PageLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpController {
    public void ToLogin(MouseEvent mouseEvent)throws IOException{
        new PageLoader().load("Login");

    }
}
