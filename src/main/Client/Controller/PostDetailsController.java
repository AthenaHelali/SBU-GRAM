package main.Client.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.*;
import main.Client.Controller.PostItemController;
import main.Common.OthersAccount;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PostDetailsController {
    public ImageView afterLike;
    public ImageView beforLike;
    public ImageView writerImage;
    public Label username;
    public Label title;
    public Label commentsNumber;
    public Label repostsNumber;
    public Label LikesNumber;
    public JFXTextArea descriptions;
    public ImageView defaultPostImage;
    public ImageView PostImage;
    public AnchorPane root;
    public static OthersAccount CurrentOtherAccount;
    public Label dateAndTime;

    public void initialize() {
        CurrentOtherAccount = ToServer.sendToServer(new getAccountbyeUsernameMessage(PostItemController.CurrentPost.getWriterUsername())).getOtherAccount();
        byte[] PImage = ToServer.sendToServer(new GetProfileImageMessage(PostItemController.CurrentPost.getWriterUsername())).getProfileImage();
        if (PImage != null)
            writerImage.setImage(new Image(new ByteArrayInputStream(PImage)));
        descriptions.setText(PostItemController.CurrentPost.getDescription());
        if (PostItemController.CurrentPost.getPostImage() != null) {
            defaultPostImage.setVisible(false);
            PostImage.setImage(new Image(new ByteArrayInputStream(PostItemController.CurrentPost.getPostImage())));
            PostImage.setVisible(true);
        }
        dateAndTime.setText(PostItemController.CurrentPost.getDateAndTime());
        username.setText(PostItemController.CurrentPost.getWriterUsername());
        title.setText(PostItemController.CurrentPost.getTitle());
        LikesNumber.setText(String.valueOf(ToServer.sendToServer(new getLikesMessage(PostItemController.CurrentPost.getTitle(), PostItemController.CurrentPost.getWriterUsername())).getLikesNumber()));
        commentsNumber.setText(String.valueOf(ToServer.sendToServer(new getCommentNumberMessage
                (PostItemController.CurrentPost.getTitle(), PostItemController.CurrentPost.getWriterUsername())).getCommentNumber()));
        if (mainPage.currentAccount.getYouLiked().contains(PostItemController.CurrentPost)) {
            afterLike.setVisible(true);
            beforLike.setVisible(false);
        }
        repostsNumber.setText(String.valueOf(ToServer.sendToServer(new GetRepostNumberMessage
                (PostItemController.CurrentPost.getTitle(), PostItemController.CurrentPost.getWriterUsername())).getRepostNum()));
    }

    public void Like(MouseEvent mouseEvent) {
        if (ToServer.sendToServer(new LikeMessage(mainPage.currentAccount.getUsername(), PostItemController.CurrentPost)).getValue()) {
            beforLike.setVisible(false);
            afterLike.setVisible(true);
            PostItemController.CurrentPost.getLike().LikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(PostItemController.CurrentPost.getLike().getNumberOfLikes()));
            mainPage.currentAccount.addYouLiked(PostItemController.CurrentPost);
        } else {
            beforLike.setVisible(true);
            afterLike.setVisible(false);
            PostItemController.CurrentPost.getLike().disLikePost(mainPage.currentAccount);
            LikesNumber.setText(String.valueOf(PostItemController.CurrentPost.getLike().getNumberOfLikes()));
            mainPage.currentAccount.RemoveYouLiked(PostItemController.CurrentPost);


        }
    }

    public void repost(MouseEvent mouseEvent) {
        if (!mainPage.currentAccount.getMyPosts().contains(PostItemController.CurrentPost)) {
            PostItemController.CurrentPost.setRepostNum(PostItemController.CurrentPost.getRepostNum() + 1);
            mainPage.currentAccount.addMyPosts(PostItemController.CurrentPost);
            ToServer.sendToServer(new repostMessage(PostItemController.CurrentPost.getTitle(), PostItemController.CurrentPost.getWriterUsername(), mainPage.currentAccount.getUsername()));
        }
    }

    public void comment(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("CommentPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ToWriterPage(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("OthersPage");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void TotimeLine(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
