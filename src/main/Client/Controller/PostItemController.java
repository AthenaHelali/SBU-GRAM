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
import main.Common.Message.*;

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
    public static Post CurrentPost;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.CurrentPost = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        byte[] PImage = ToServer.sendToServer(new GetProfileImageMessage(CurrentPost.getWriterUsername())).getProfileImage();
        if (PImage != null)
            writerImage.setImage(new Image(new ByteArrayInputStream(PImage)));
        username.setText(CurrentPost.getWriterUsername());
        title.setText(CurrentPost.getTitle());
        LikesNumber.setText(String.valueOf(ToServer.sendToServer(new getLikesMessage(CurrentPost.getTitle(), CurrentPost.getWriterUsername())).getLikesNumber()));
        commentsNumber.setText(String.valueOf(ToServer.sendToServer(new getCommentNumberMessage
                (PostItemController.CurrentPost.getTitle(), PostItemController.CurrentPost.getWriterUsername())).getCommentNumber()));
        if (mainPage.currentAccount.getYouLiked().contains(CurrentPost)) {
            afterLike.setVisible(true);
            beforLike.setVisible(false);
        }
        repotsNumber.setText(String.valueOf(ToServer.sendToServer
                (new GetRepostNumberMessage(CurrentPost.getTitle(), CurrentPost.getWriterUsername())).getRepostNum()));
        return root;
    }


    public void Like(MouseEvent mouseEvent) {
        if (ToServer.sendToServer(new LikeMessage(mainPage.currentAccount.getUsername(), CurrentPost)).getValue()) {
            beforLike.setVisible(false);
            afterLike.setVisible(true);
            CurrentPost.getLike().LikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(CurrentPost.getLike().getNumberOfLikes()));
            mainPage.currentAccount.addYouLiked(CurrentPost);

        } else {
            beforLike.setVisible(true);
            afterLike.setVisible(false);
            CurrentPost.getLike().disLikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(CurrentPost.getLike().getNumberOfLikes()));
            mainPage.currentAccount.RemoveYouLiked(CurrentPost);


        }
    }

    public void comment(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("CommentPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void repost(MouseEvent mouseEvent) {
        if (!mainPage.currentAccount.getMyPosts().contains(CurrentPost)) {
            CurrentPost.setRepostNum(CurrentPost.getRepostNum() + 1);
            mainPage.currentAccount.addMyPosts(CurrentPost);
            ToServer.sendToServer(new repostMessage(CurrentPost.getTitle(), CurrentPost.getWriterUsername(), mainPage.currentAccount.getUsername()));
        }

    }

    public void detail(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("PostDetail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
