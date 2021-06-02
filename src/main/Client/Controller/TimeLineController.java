package main.Client.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.Common.Post;

import java.util.ArrayList;

public class TimeLineController {
    public ListView<Post> postList;
    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();

    @FXML
    public void initialize() {
        //initialize posts array list to be shown in list view
        for (int i = 1; i <= 5; i++) {
            Post p = new Post();
            p.setTitle("post" + i);
            p.setDescription("description" + i);
            p.setWriterUsername("user" + i);
            posts.add(p);
        }

        //show the post array in list view
        postList.setItems(FXCollections.observableArrayList(posts));

        //customize each cell of postList with new graphic object PostItem
        postList.setCellFactory(postList -> new PostItem());
    }

    public void addPost(ActionEvent actionEvent) {
        //set the post features

        /*
        if the listview cells are not customized and list view is made of strings
        this code will add new post title to the list view
        postList.getItems().add(currentPost.getTitle());
         */

    }


    //this function is usable for uncustomized_cell listview of strings
    public void showPost(MouseEvent mouseEvent) {

    }

}
