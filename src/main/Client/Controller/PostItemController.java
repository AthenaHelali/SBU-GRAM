package main.Client.Controller;

import com.jfoenix.controls.JFXTextArea;
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
    public Label commentsNumber;
    public Label repotsNumber;
    public JFXTextArea descriptions;
    public ImageView PostImage;
    public ImageView defaultPostImage;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        if(post.getWriterImage()!=null)
        writerImage.setImage(new Image(new ByteArrayInputStream(post.getWriterImage())));
        username.setText(post.getWriterUsername());
        title.setText(post.getTitle());
        if(post.getPostImage()!=null){
            PostImage.setImage(new Image(new ByteArrayInputStream(post.getPostImage())));
            defaultPostImage.setVisible(false);
            PostImage.setVisible(true);
        }
        descriptions.setText(post.getDescription());
        LikesNumber.setText(String.valueOf(post.getLike().getNumberOfLikes()));
        commentsNumber.setText(String.valueOf(post.getComments().size()));
        if(mainPage.currentAccount.getYouLiked().contains(post)){
            afterLike.setVisible(true);
            beforLike.setVisible(false);
        }
        return root;
    }

    public void details(MouseEvent mouseEvent) {

    }

    public void Like(MouseEvent mouseEvent) {
        if(ToServer.sendToServer(new LikeMessage(mainPage.currentAccount,post)).getValue()){
            beforLike.setVisible(false);
            afterLike.setVisible(true);
            post.getLike().LikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(post.getLike().getNumberOfLikes()));
            mainPage.currentAccount.addYouLiked(post);

        }
        else {
            beforLike.setVisible(true);
            afterLike.setVisible(false);
            post.getLike().disLikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(post.getLike().getNumberOfLikes()));
            mainPage.currentAccount.RemoveYouLiked(post);


        }
    }

    public void comment(MouseEvent mouseEvent) {
    }

    public void repost(MouseEvent mouseEvent) {
    }

    public void ToWriterPage(MouseEvent mouseEvent) {
    }
    /*
    you can also add on mouse click for like and repost image 
     */
}
