package Client.Controller;

import Client.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {


    public TextField Login_usernameField;
    public Label WrongPassword;
    public PasswordField passwordField;
    public TextField Password_Visible;
    public Label Login_to_SignUp;
    public Label ForgetPassword_Field;

    public void Login(ActionEvent actionEvent) throws IOException {
        String Username=Login_usernameField.getText();
        String Password;
        if (passwordField.isVisible())
            Password=passwordField.getText();
        else
            Password=Password_Visible.getText();
        if(Username.equalsIgnoreCase("Ali")&&Password.equalsIgnoreCase("alavi")){
            new PageLoader().load("timeLine");
            WrongPassword.setVisible(false);
        }
        else{
            WrongPassword.setVisible(true);

        }
    }

    public void ShowPassword(ActionEvent actionEvent) {
        if(!Password_Visible.isVisible()){
            Password_Visible.setVisible(true);
            passwordField.setVisible(false);
            Password_Visible.setText(passwordField.getText());
        }
        else {
            Password_Visible.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(Password_Visible.getText());
        }
    }

    public void signUp(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("signUp");
    }
}
