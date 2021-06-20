package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.Client.ToServer;
import main.Client.model.PageLoader;
import main.Common.Message.GetAllProfilesMessage;
import main.Common.OthersAccount;

import java.io.IOException;
import java.util.ArrayList;

public class SearchController {
    public ListView ProfileList;
    public ArrayList<OthersAccount>othersAccounts;
    @FXML
    public void initialize() {
        ArrayList<OthersAccount>othersAccounts= ToServer.sendToServer(new GetAllProfilesMessage()).getOthersAccounts();
        ProfileList.setItems(FXCollections.observableArrayList(othersAccounts));
        ProfileList.setCellFactory(ProfileList -> new ProfileItem());
    }

    public void menue(MouseEvent mouseEvent){
        try {
            new PageLoader().load("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(MouseEvent mouseEvent) {
        try {
            new PageLoader().load("searchPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
