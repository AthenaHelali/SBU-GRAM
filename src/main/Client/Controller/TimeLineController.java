package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Post;
import main.Common.Message.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public ListView<Post> postList;
    public ImageView ProfileImage;
    public Label Username;
    public ImageView defultProfileImage;
    private ArrayList<Post> posts;
    Post currentPost = new Post();

    @FXML
    public void initialize() {
        if (mainPage.currentAccount.getProfileImage() != null) {
            ProfileImage.setImage(new Image(new ByteArrayInputStream(mainPage.currentAccount.getProfileImage())));
            ProfileImage.setVisible(true);
            defultProfileImage.setVisible(false);
        }
        posts=ToServer.sendToServer(new timelinePostsMessage(mainPage.currentAccount.getUsername())).getPosts();
        Username.setText(mainPage.currentAccount.getUsername());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }


    //this function is usable for uncustomized_cell listview of strings
    public void showPost(MouseEvent mouseEvent) {
        Post post = postList.getSelectionModel().getSelectedItem();


    }

    public void refresh(MouseEvent mouseEvent) {
    }

    public void menue(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
