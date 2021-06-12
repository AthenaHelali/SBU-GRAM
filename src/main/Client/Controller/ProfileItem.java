package main.Client.Controller;

import javafx.scene.control.ListCell;
import main.Common.OthersAccount;

import java.io.IOException;

public class ProfileItem extends ListCell<OthersAccount> {
    @Override
    public void updateItem(OthersAccount othersAccount, boolean empty) {
        super.updateItem(othersAccount, empty);
        if (othersAccount != null) {
            try {
                setGraphic(new ProfileItemController(othersAccount).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
