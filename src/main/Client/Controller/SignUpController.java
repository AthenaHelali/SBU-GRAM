package main.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import javafx.scene.input.MouseEvent;
import main.Client.model.mainPage;
import main.Common.Account;
import main.Common.Message.SignUpMessage;
import main.Common.Message.UserExistMessage;

import java.io.*;

public class SignUpController {

    public CheckBox showPassword_checkbox;
    public Label ForgetPassword_Field;
    public Label ForgetPassword_Field1;
    public JFXButton Signup_botton;
    public ImageView image;
    public ImageView chooseImage;
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField PasswordShow;
    public TextField Firstname;
    public TextField Lastname;
    public PasswordField ConfirmPasswordField;
    public TextField ConfirmPasswordShow;
    public CheckBox showConfirmPassword_checkbox;
    public TextField Email;
    public Label toLogin;
    public byte[] profileImage;

    public void ToLogin(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Login");

    }


    public void SetProfileImage(MouseEvent mouseEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            FileInputStream fileInputStream = new FileInputStream(file);
            profileImage = fileInputStream.readAllBytes();
            Image image1 = new Image(new ByteArrayInputStream(profileImage));
            image.setImage(image1);
            chooseImage.setVisible(false);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SignUp(ActionEvent actionEvent) {
        String Username = usernameField.getText();
        String Password;
        String ConfirmPassword;
        if (passwordField.isVisible())
            Password = passwordField.getText();
        else
            Password = ConfirmPasswordShow.getText();

        if (ConfirmPasswordField.isVisible())
            ConfirmPassword = passwordField.getText();
        else
            ConfirmPassword = ConfirmPasswordShow.getText();

        String firstname = Firstname.getText();
        String lastname = Lastname.getText();
        String email = Email.getText();

        if (!ToServer.sendToServer(new UserExistMessage(Username)).getValue()) {
            Account account = new Account(firstname, lastname, Username, Password, email);
            if (profileImage!=null)
            account.setProfileImage(profileImage);
            if (ToServer.sendToServer(new SignUpMessage(account)).getValue()) {
                mainPage.currentAccount = account;
                try {
                    new PageLoader().load("timeLine");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void showPass(ActionEvent actionEvent) {
        if (!PasswordShow.isVisible()) {
            PasswordShow.setVisible(true);
            passwordField.setVisible(false);
            PasswordShow.setText(passwordField.getText());
        } else {
            PasswordShow.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(PasswordShow.getText());
        }
    }

    public void showConfPass(ActionEvent actionEvent) {
        if (!ConfirmPasswordShow.isVisible()) {
            ConfirmPasswordShow.setVisible(true);
            ConfirmPasswordField.setVisible(false);
            ConfirmPasswordShow.setText(ConfirmPasswordField.getText());
        } else {
            ConfirmPasswordShow.setVisible(false);
            ConfirmPasswordField.setVisible(true);
            ConfirmPasswordField.setText(ConfirmPasswordShow.getText());
        }
    }
}
