package main.Client.Controller;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.Common.Message.LikeMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
public class PostItemController {
    public AnchorPane root;
    public ImageView writerImage;
    public Label username;
    public Label title;
    public ImageView beforLike;
    public ImageView afterLike;
    public Label LikesNumber;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        writerImage.setImage(new Image(new ByteArrayInputStream(post.getWriterImage())));
        username.setText(post.getWriterUsername());
        title.setText(post.getTitle());

        return root;
    }

    public void details(MouseEvent mouseEvent) {

    }

    public void Like(MouseEvent mouseEvent) {
        if(ToServer.sendToServer(new LikeMessage(mainPage.cerrentAccount,post)).getValue()){
            beforLike.setVisible(false);
            afterLike.setVisible(true);
            post.getLike().LikePost(mainPage.cerrentAccount);
            LikesNumber.setText(String.valueOf(post.getLike().getNumberOfLikes()));
            mainPage.cerrentAccount.addYouLiked(post);

        }
        else {
            beforLike.setVisible(true);
            afterLike.setVisible(false);
            post.getLike().disLikePost(mainPage.cerrentAccount);
            LikesNumber.setText(String.valueOf(post.getLike().getNumberOfLikes()));
            mainPage.cerrentAccount.RemoveYouLiked(post);


        }
    }

    public void comment(MouseEvent mouseEvent) {
    }

    public void repost(MouseEvent mouseEvent) {
    }
    /*
    you can also add on mouse click for like and repost image 
     */
}
