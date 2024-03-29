package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.MyPostsMessage;
import main.Common.Message.getFollowersNumberMessage;
import main.Common.Post;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

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
        posts = ToServer.sendToServer(new MyPostsMessage(mainPage.currentAccount.getUsername())).getPosts();
        if (posts != null) {
            posts.sort(new Comparator<Post>() {
                @Override
                public int compare(Post o1, Post o2) {
                    return (int) (o2.getMiliTime() - o1.getMiliTime());
                }
            });
        }
        if (mainPage.currentAccount.getProfileImage() != null) {
            ProfileImage1.setImage(new Image(new ByteArrayInputStream(mainPage.currentAccount.getProfileImage())));
            ProfileImage1.setVisible(true);
            defaultProfileImage1.setVisible(false);
        }
        Username1.setText(mainPage.currentAccount.getUsername());
        firstname.setText(mainPage.currentAccount.getFirstName());
        lastname.setText(mainPage.currentAccount.getLastName());
        Followers.setText(String.valueOf(ToServer.sendToServer(new getFollowersNumberMessage(mainPage.currentAccount.getUsername())).getFollowers()));
        String temp = String.valueOf(mainPage.currentAccount.getFollowing().size());
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
