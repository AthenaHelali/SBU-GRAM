package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Comment;
import main.Common.Message.NewCommentMessage;
import main.Common.Message.getCommentsMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CommentPageController {
    public ImageView defaultImage;
    public ImageView Image;
    public TextField AddComment;
    public ArrayList<Comment>comments;
    public ListView CommentsList;

    public void initialize() {
        comments= ToServer.sendToServer(new getCommentsMessage(PostItemController.CurrentPost.getWriterUsername(),PostItemController.CurrentPost.getTitle())).getComments();
        if(mainPage.currentAccount.getProfileImage()!=null){
            Image.setImage(new Image(new ByteArrayInputStream(mainPage.currentAccount.getProfileImage())));
            Image.setVisible(true);
            defaultImage.setVisible(false);
        }
        CommentsList.setItems(FXCollections.observableArrayList(comments));
       CommentsList.setCellFactory(CommentList -> new CommentItem());


    }


    public void ToPost(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("PostDetail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendComment(ActionEvent actionEvent) {
        Comment comment=new Comment(mainPage.currentAccount.getUsername(),AddComment.getText().trim());
        PostItemController.CurrentPost.AddComent(comment);
        ToServer.sendToServer(new NewCommentMessage(comment,PostItemController.CurrentPost.getTitle(),PostItemController.CurrentPost.getWriterUsername()));
        try {
            new PageLoader().load("commentPage");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
