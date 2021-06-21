package main.Client.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.FollowMessage;
import main.Common.Message.UnfollowMessage;
import main.Common.Post;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class OthersPageController {
    public Label bio;
    public ImageView defaultProfileImage1;
    public Label Username1;
    public Label firstname;
    public Label Followers;
    public Label Following;
    public Label Posts;
    public Label lastname;
    public JFXButton FollowButton;
    public JFXButton FollowingButton;
    public ImageView ProfileImage2;
    public JFXListView PostList2;
    public Label location2;
    ArrayList<Post>posts;
    public void initialize() {
        posts=PostDetailsController.CurrentOtherAccount.getPosts();
        Collections.sort(posts,(a, b)->a.getMiliTime()-b.getMiliTime()>0?-1:a.getMiliTime()-b.getMiliTime()==0?0:1);

        if(mainPage.currentAccount.getProfileImage()!=null) {
            ProfileImage2.setImage(new Image(new ByteArrayInputStream(PostDetailsController.CurrentOtherAccount.getProfileImage())));
            ProfileImage2.setVisible(true);
            defaultProfileImage1.setVisible(false);
        }
        Username1.setText(PostDetailsController.CurrentOtherAccount.getUsername());
        firstname.setText(PostDetailsController.CurrentOtherAccount.getFirstName());
        lastname.setText(PostDetailsController.CurrentOtherAccount.getLastName());
        String temp = String.valueOf(PostDetailsController.CurrentOtherAccount.getFollowers().size());
        Followers.setText(temp);
        temp = String.valueOf(PostDetailsController.CurrentOtherAccount.getFollowing().size());
        Following.setText(temp);
        Posts.setText(String.valueOf(PostDetailsController.CurrentOtherAccount.getPosts().size()));
        if (PostDetailsController.CurrentOtherAccount.getLocation()!= null)
            location2.setText(PostDetailsController.CurrentOtherAccount.getLocation());
        else location2.setText("");
        if (PostDetailsController.CurrentOtherAccount.getBio()!= null)
            bio.setText(PostDetailsController.CurrentOtherAccount.getBio());
        else bio.setText("");
        PostList2.setItems(FXCollections.observableArrayList(posts));
        PostList2.setCellFactory(postList -> new PostItem());
    }

    public void TotimeLine(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Follow(ActionEvent actionEvent) {
        ToServer.sendToServer(new FollowMessage(mainPage.currentAccount.getUsername(),PostDetailsController.CurrentOtherAccount.getUsername()));
        mainPage.currentAccount.Follow(PostDetailsController.CurrentOtherAccount);
        FollowButton.setVisible(false);
        FollowingButton.setVisible(true);
    }

    public void Following(ActionEvent actionEvent) {
        ToServer.sendToServer(new UnfollowMessage(mainPage.currentAccount.getUsername(),PostDetailsController.CurrentOtherAccount.getUsername()));
        mainPage.currentAccount.Unfollow(PostDetailsController.CurrentOtherAccount);
    }
}
