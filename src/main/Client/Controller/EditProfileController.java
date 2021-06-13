package main.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.UpdateProfileMessage;

import java.io.*;

public class EditProfileController {

    public ImageView defaultProfileImage;
    public TextField FirstName;
    public TextField Lastname;
    public TextField email;
    public TextField password;
    public TextField Location;
    public TextField bio;
    public ImageView ProfileImage;
    public byte[] image=null;
    @FXML
    public void initialize() {
        FirstName.setText(mainPage.currentAccount.getFirstName());
        Lastname.setText(mainPage.currentAccount.getLastName());
        email.setText(mainPage.currentAccount.getEmail());
        password.setText(mainPage.currentAccount.getPassword());
        if(mainPage.currentAccount.getLocation()==null)
            Location.setText("");
        else Location.setText(mainPage.currentAccount.getLocation());
        if(mainPage.currentAccount.getBio()==null)
            bio.setText("");
        else bio.setText(mainPage.currentAccount.getBio());
        if(mainPage.currentAccount.getProfileImage()!=null){
            image=mainPage.currentAccount.getProfileImage();
            defaultProfileImage.setVisible(false);
            ProfileImage.setImage(new Image(new ByteArrayInputStream(mainPage.currentAccount.getProfileImage())));
            ProfileImage.setVisible(true);
        }
    }

    public void Cancel(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Done(MouseEvent mouseEvent) {
        mainPage.currentAccount.setFirstName(FirstName.getText());
        mainPage.currentAccount.setLastName(Lastname.getText());
        mainPage.currentAccount.setBio(bio.getText());
        mainPage.currentAccount.setLocation(Location.getText());
        mainPage.currentAccount.setProfileImage(image);
        mainPage.currentAccount.setPassword(password.getText());
        ToServer.sendToServer(new UpdateProfileMessage(mainPage.currentAccount));
            try {
                new PageLoader().load("timeLine");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void changeProfilePhoto(MouseEvent mouseEvent) {
        try {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream = new FileInputStream(file);
        image = fileInputStream.readAllBytes();
        ProfileImage.setImage(new Image(new ByteArrayInputStream(image)));
            if(defaultProfileImage.isVisible()){
                defaultProfileImage.setVisible(false);
                ProfileImage.setVisible(true);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
