package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.GetMyAccountMessage;
import main.Common.Message.MyPostsMessage;
import main.Common.Post;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class pageController {

    public ImageView ProfileImage1;
    public Label Username1;
    public Label bio;
    public Label firstname;
    public Label lastname;
    public Label location1;
    public Label Following;
    public Label Followers;
    public ListView postList1;
    public Label Posts;
    public ImageView defaultProfileImage1;
    private ArrayList<Post> posts;

    @FXML
    public void initialize() {
        mainPage.currentAccount=ToServer.sendToServer(new GetMyAccountMessage(mainPage.currentAccount.getUsername())).getAccount();
        posts= ToServer.sendToServer(new MyPostsMessage(mainPage.currentAccount.getUsername())).getPosts();
        Collections.sort(posts,(a, b)->a.getMiliTime()-b.getMiliTime()>0?-1:a.getMiliTime()-b.getMiliTime()==0?0:1);

        if(mainPage.currentAccount.getProfileImage()!=null) {
            ProfileImage1.setImage(new Image(new ByteArrayInputStream(mainPage.currentAccount.getProfileImage())));
            ProfileImage1.setVisible(true);
            defaultProfileImage1.setVisible(false);
        }
        Username1.setText(mainPage.currentAccount.getUsername());
        firstname.setText(mainPage.currentAccount.getFirstName());
        lastname.setText(mainPage.currentAccount.getLastName());
        String temp = String.valueOf(mainPage.currentAccount.getFollowers().size());
        Followers.setText(temp);
        temp = String.valueOf(mainPage.currentAccount.getFollowing().size());
        Following.setText(temp);
        Posts.setText(String.valueOf(mainPage.currentAccount.getMyPosts().size()));
        if (mainPage.currentAccount.getLocation() != null)
            location1.setText(mainPage.currentAccount.getLocation());
        else location1.setText("");
        if (mainPage.currentAccount.getBio() != null)
            bio.setText(mainPage.currentAccount.getBio());
        else bio.setText("");
        postList1.setItems(FXCollections.observableArrayList(posts));
        postList1.setCellFactory(postList -> new PostItem());
    }

    public void TotimeLine(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditProfile(ActionEvent actionEvent) {
        try {
            new PageLoader().load("EditProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
