package main.Client.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Common.Account;
import main.Common.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController {
    public TableView <Account>AccountsTable;
    public TextField SearchField;
    public TableColumn <Account,String>accounts;
    private final ObservableList<Post> PostList= FXCollections.observableArrayList();

}
