package main.Client.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Post;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public ListView<Post> postList;
    public ImageView ProfileImage;
    public Label Username;
    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();

    @FXML
    public void initialize() {
        ProfileImage.setImage(new Image(new ByteArrayInputStream(mainPage.cerrentAccount.getProfileImage())));
        Username.setText(mainPage.cerrentAccount.getUsername());
        //initialize posts array list to be shown in list view
        //TODO
        //show the post array in list view
        postList.setItems(FXCollections.observableArrayList(posts));

        //customize each cell of postList with new graphic object PostItem
        postList.setCellFactory(postList -> new PostItem());
    }


    //this function is usable for uncustomized_cell listview of strings
    public void showPost(MouseEvent mouseEvent) {

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
