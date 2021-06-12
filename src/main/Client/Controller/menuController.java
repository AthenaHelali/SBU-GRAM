package main.Client.Controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Common.Message.LogOutMessage;

import java.io.IOException;

public class menuController {
    public void ToMyProfile(ActionEvent actionEvent) {
        try {
            new PageLoader().load("page");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        ToServer.sendToServer(new LogOutMessage());
        ToServer.disconnectFromServer();
    }

    public void BackTotimeline(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Search(ActionEvent actionEvent) {
        try {
            new PageLoader().load("searchPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
