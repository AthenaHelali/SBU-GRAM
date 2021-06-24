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
import java.util.regex.Pattern;

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
    public Label invalidPassword;
    public Label usernameAlreadyTaken;
    public Label PasswordDoesnotMatch;
    public Label emptyLastname;
    public Label emptyFirstname;
    public Label emptyUsername;
    public Label emptyEmail;
    public TextField question;
    public Label emptyQuestion;

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
        String Question = question.getText();
        String Username = usernameField.getText();
        String Password;
        String ConfirmPassword;
        String firstname = Firstname.getText();
        String lastname = Lastname.getText();
        String email = Email.getText();
        if (passwordField.isVisible())
            Password = passwordField.getText().trim();
        else
            Password = PasswordShow.getText().trim();

        if (ConfirmPasswordField.isVisible())
            ConfirmPassword = ConfirmPasswordField.getText().trim();
        else
            ConfirmPassword = ConfirmPasswordShow.getText();

        if (ToServer.sendToServer(new UserExistMessage(Username)).getValue()) {
            usernameAlreadyTaken.setVisible(true);
            Username = null;
        } else {
            if (usernameAlreadyTaken.isVisible())
                usernameAlreadyTaken.setVisible(false);
        }
        if (Password == null | Password.length() < 8 | !Pattern.matches("[a-zA-Z_0-9]+", Password)) {
            invalidPassword.setVisible(true);
            Password = null;
        } else {
            if (invalidPassword.isVisible())
                invalidPassword.setVisible(false);
            if (!Password.equals(ConfirmPassword)) {
                PasswordDoesnotMatch.setVisible(true);
                Password = null;
            } else {
                if (PasswordDoesnotMatch.isVisible()) {
                    PasswordDoesnotMatch.setVisible(false);
                }
            }
        }

        if (firstname == null | firstname.length() == 0) {
            emptyFirstname.setVisible(true);
            firstname = null;
        } else {
            if (emptyFirstname.isVisible())
                emptyFirstname.setVisible(false);
        }
        if (lastname == null | lastname.length() == 0) {
            emptyLastname.setVisible(true);
            lastname = null;
        } else {
            if (emptyLastname.isVisible())
                emptyLastname.setVisible(false);
        }
        if (email == null | email.length() == 0) {
            emptyEmail.setVisible(true);
            email = null;
        } else {
            if (emptyEmail.isVisible())
                emptyEmail.setVisible(false);
        }
        if (Question == null | Question.length() == 0) {
            emptyQuestion.setVisible(true);
            Question = null;
        } else if (emptyQuestion.isVisible())
            emptyQuestion.setVisible(false);

        if (Username != null && firstname != null && lastname != null && email != null && Password != null && Question != null) {
            Account account = new Account(firstname, lastname, Username, Password, email, Question);
            if (profileImage != null)
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
