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
    public ArrayList<OthersAccount>othersAccounts= ToServer.sendToServer(new GetAllProfilesMessage()).getOthersAccounts();
    @FXML
    public void initialize() {
        ProfileList.setItems(FXCollections.observableArrayList(othersAccounts));
        ProfileList.setCellFactory(ProfileList -> new ProfileItem());
    }

    public void menue(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void refresh(MouseEvent mouseEvent) {
    }
}
