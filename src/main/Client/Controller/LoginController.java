package main.Client.Controller;

import main.Client.ToServer;
import main.Client.model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Client.model.mainPage;
import main.Common.Message.AnswerMessage;
import main.Common.Message.LoginMessage;
import main.Common.Message.UserExistMessage;

import java.io.IOException;

public class LoginController {


    public TextField Login_usernameField;
    public Label WrongPassword;
    public PasswordField passwordField;
    public TextField Password_Visible;
    public Label Login_to_SignUp;
    public Label ForgetPassword_Field;
    public Label WrongUsername;

    public void Login(ActionEvent actionEvent) throws IOException {
        String Username = Login_usernameField.getText();
        if (!ToServer.sendToServer(new UserExistMessage(Username)).getValue()) {
            if(!WrongUsername.isVisible())
            WrongUsername.setVisible(true);
        }
        else {
            if(WrongUsername.isVisible())
                WrongUsername.setVisible(false);
        }
            String Password;
            if (passwordField.isVisible())
                Password = passwordField.getText();
            else
                Password = Password_Visible.getText();
            AnswerMessage answerMessage=ToServer.sendToServer(new LoginMessage(Username,Password));
            if (answerMessage.getValue()) {
                mainPage.cerrentAccount =answerMessage.getProfile();
                new PageLoader().load("timeLine");
                WrongPassword.setVisible(false);
            } else {
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
