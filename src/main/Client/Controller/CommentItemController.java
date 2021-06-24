package main.Client.Controller;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Common.Comment;
import main.Common.Message.GetProfileImageMessage;
import main.Common.Post;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CommentItemController {
    public ImageView defaultImage;
    public ImageView ProfileImage;
    public Label username2;
    public Label comment;
    public Comment cmt;
    public AnchorPane root;
    public byte[] image = null;

    public CommentItemController(Comment comment) throws IOException {
        new PageLoader().load("CommentItem", this);
        this.cmt = comment;
    }

    public AnchorPane init() {
        image = ToServer.sendToServer(new GetProfileImageMessage(cmt.getWriterUsername())).getProfileImage();
        if (image != null) {
            ProfileImage.setImage(new Image(new ByteArrayInputStream(image)));
            ProfileImage.setVisible(true);
            defaultImage.setVisible(false);
        }
        username2.setText(cmt.getWriterUsername());
        comment.setText(cmt.getDescriptions());
        return root;
    }
}
