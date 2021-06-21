package main.Client.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Client.model.mainPage;
import main.Common.Message.FollowMessage;
import main.Common.Message.UpdateDataMessage;
import main.Common.Message.UpdateProfileMessage;
import main.Common.Message.getAccountbyeUsernameMessage;
import main.Common.OthersAccount;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ProfileItemController {

    public AnchorPane root2;
    public Label username2;
    public Label bio2;
    public Label firstname2;
    public Label lastname2;
    public Label location2;
    public JFXButton following;
    public JFXButton Follow;
    public ImageView ProfileImage2;
    public OthersAccount othersAccount;

    public ProfileItemController(OthersAccount othersAccount) throws IOException {
        new PageLoader().load("ProfileItem", this);
        this.othersAccount =othersAccount ;
    }
    public AnchorPane init() {
        if(othersAccount.getProfileImage()!=null)
            ProfileImage2.setImage(new Image(new ByteArrayInputStream(othersAccount.getProfileImage())));
        username2.setText(othersAccount.getUsername());
        firstname2.setText(othersAccount.getFirstName());
        lastname2.setText(othersAccount.getLastName());
        if(othersAccount.getBio()!=null){
            bio2.setText(othersAccount.getBio());
        }
        else bio2.setText("");
        if(othersAccount.getLocation()!=null){
            location2.setText(othersAccount.getLocation());
        }
        else location2.setText("");
        if (mainPage.currentAccount.getFollowing().stream().map(a->a.getUsername()).filter(a->a.equals(othersAccount.getUsername())).count()!=0){
            Follow.setVisible(false);
            following.setVisible(true);
        }

        return root2;
    }
    public void Follow(ActionEvent actionEvent)  {
       ToServer.sendToServer(new FollowMessage(mainPage.currentAccount.getUsername(),othersAccount.getUsername()));
       mainPage.currentAccount.Follow(othersAccount);
       Follow.setVisible(false);
       following.setVisible(true);
    }
    public void ToPage(ActionEvent actionEvent) {
        PostDetailsController.CurrentOtherAccount=othersAccount;
        try {
            new PageLoader().load("OthersPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
