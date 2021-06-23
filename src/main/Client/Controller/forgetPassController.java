package main.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Common.Message.UserExistMessage;
import main.Common.Message.forgetPasswordMessage;

import java.io.IOException;

public class forgetPassController {
    public TextField username;
    public TextField question;
    public JFXButton ShowPassButton;
    public Rectangle rect2;
    public TextField MyPassword;
    public Label WrongUsername;
    public Label wrongAnswer;

    public void ShowPass(ActionEvent actionEvent) {
        String User=username.getText();
        String Question= question.getText();
        if (!ToServer.sendToServer(new UserExistMessage(User)).getValue()) {
            if(!WrongUsername.isVisible())
                WrongUsername.setVisible(true);
        }else {
            if (WrongUsername.isVisible())
                WrongUsername.setVisible(false);
            if (!ToServer.sendToServer(new forgetPasswordMessage(User, Question)).getValue()) {
                wrongAnswer.setVisible(true);
                Question = null;
            } else if (wrongAnswer.isVisible())
                wrongAnswer.setVisible(false);
            if (Question != null && User != null) {
                MyPassword.setText(ToServer.sendToServer(new forgetPasswordMessage(User, Question)).getPassword());
                MyPassword.setVisible(true);
                rect2.setVisible(true);
            }
        }
    }

    public void toLogin(MouseEvent mouseEvent) {
        MyPassword.setVisible(false);
        rect2.setVisible(false);
        try {
            new PageLoader().load("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
