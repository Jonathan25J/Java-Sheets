import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Menu extends Application {
    @FXML
    Button edit;
    @FXML
    Button cards;
    public void initialize() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = loader.load();
        stage.setTitle("Menu");
        stage.setResizable(false);
        root.setStyle("-fx-background-color: #978fc3");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void getCards() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu/options/fxml/profiles.fxml"));
        Parent root =  (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Profiles");
        stage.setResizable(false);
        stage.setMaximized(true);
        root.setStyle("-fx-background-color: lightblue");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    public void getEdit() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu/edit/fxml/edit.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit");
        stage.setResizable(false);
        root.setStyle("-fx-background-color: #a7daad");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}