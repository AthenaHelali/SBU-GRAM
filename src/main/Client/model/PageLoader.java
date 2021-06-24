package main.Client.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    public static final int Height = 640, Width = 415;
    public static Stage stage;
    public static Scene scene;

    public static void initStage(Stage PrimaryStage) {
        stage = PrimaryStage;
        stage.setTitle("SBU GRAM");
        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Paths.get("images\\logo2.png").toUri().toString()));
    }

    public Parent LoadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainPage.class.getResource("/main/Client/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void load(String url) throws IOException {
        scene = new Scene(new PageLoader().LoadFXML(url));
        stage.setScene(scene);
        stage.show();
    }

    public void load(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainPage.class.getResource("/main/Client/View/" + fxml + ".fxml"));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }

}
