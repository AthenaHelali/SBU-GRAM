package main.Client.Controller;

import javafx.scene.input.MouseEvent;
import main.Client.model.PageLoader;
import main.Common.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
public class PostItemController {
    public AnchorPane root;
    public ImageView profileImage;
    public Label username;
    public Label title;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username.setText(post.getWriterUsername());
        title.setText(post.getTitle());

        return root;
    }

    public void details(MouseEvent mouseEvent) {
    }

    public void Like(MouseEvent mouseEvent) {
    }

    public void comment(MouseEvent mouseEvent) {
    }

    public void repost(MouseEvent mouseEvent) {
    }
    /*
    you can also add on mouse click for like and repost image 
     */
}
