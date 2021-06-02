package Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    public static final int Height=640,Width=400;
    public static Stage stage;
    public static Scene scene;
    public static void initStage(Stage PrimaryStage){
        stage=PrimaryStage;
        stage.setTitle("SBU GRUM");
        stage.setWidth(Width);
        stage.setHeight(Height);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Paths.get("images\\logo2.png").toUri().toString()));
    }
    public Parent LoadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("/Client/View/" +fxml+".fxml"));
        return fxmlLoader.load();
    }
    public void load(String url)throws IOException{
        scene=new Scene(new PageLoader().LoadFXML(url));
        stage.setScene(scene);
        stage.show();
    }

}
