package main.Client.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.NewPostMessage;
import main.Common.Post;
import main.server.Server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

public class newPostController {
    public TextField title;
    public TextArea caption;
    public ImageView PostImage;
    public Label DuplicateTitle;
    private String decription;
    private String Posttitle;
    private  byte[] Image;
    private Post post;

    public void Share(ActionEvent actionEvent) {
        post=new Post(title.getText(), mainPage.cerrentAccount.getUsername(),caption.getText());
        if(!mainPage.cerrentAccount.getMyPosts().stream().map(a->a.getTitle()).collect(Collectors.toList()).contains(title.getText())){
            ToServer.sendToServer(new NewPostMessage(post));
            post.setPostImage(Image);
            mainPage.cerrentAccount.NewPost(post);
            DuplicateTitle.setVisible(false);
            try {
                new PageLoader().load("timeLine");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            DuplicateTitle.setVisible(true);
    }

    public void Clear(ActionEvent actionEvent) {
        title.setText("");
        caption.setText("");
        PostImage.setImage(null);
    }

    public void addImage(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            FileInputStream fileInputStream = new FileInputStream(file);
            Image image1 = new Image(new ByteArrayInputStream(fileInputStream.readAllBytes()));
            PostImage.setImage(image1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ToTimeLine(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("timeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
