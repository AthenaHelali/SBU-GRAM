package Client.Controller;

import Common.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class timelineController {
    public TextField title_field;
    public TextArea descrip_field;
    public Button Publish_button;
    public ListView<String> PostList;
    ArrayList<Post>posts=new ArrayList<>();
    Post CurrentPost=new Post();

    public void addPost(ActionEvent actionEvent) {
        CurrentPost.setWriterUsername("ali alavi");
        CurrentPost.setDescription(descrip_field.getText());
        CurrentPost.setTitle(title_field.getText());
        posts.add(CurrentPost);
        PostList.getItems().add(CurrentPost.getTitle());
        CurrentPost=new Post();
        title_field.setText("");
        descrip_field.setText("");
    }

    public void publicPost(ActionEvent actionEvent) {
    }

    public void privatePost(ActionEvent actionEvent) {
    }

    public void showPost(MouseEvent mouseEvent) {
    }
}
