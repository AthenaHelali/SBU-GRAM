package main.Client.Controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import main.Client.model.PageLoader;

import java.io.IOException;

public class menuController {
    public void ToMyProfile(ActionEvent actionEvent) {
    }

    public void ToDirect(ActionEvent actionEvent) {
    }

    public void ToNewPost(ActionEvent actionEvent) {
        try {
            new PageLoader().load("newPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOut(ActionEvent actionEvent) {
    }

    public void BackTotimeline(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
