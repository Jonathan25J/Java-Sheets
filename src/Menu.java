import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setTitle("Java Sheets");
        stage.setResizable(false);
        root.setId("menu");
        root.getStylesheets().addAll(this.getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void getCards() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu/options/fxml/profiles.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setTitle("Profiles");
        stage.setResizable(false);
        root.setId("profiles");
        root.getStylesheets().addAll(this.getClass().getResource("/css/styles.css").toExternalForm());
        stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    public void getEdit() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu/edit/fxml/edit.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setTitle("Edit");
        stage.setResizable(false);
        root.setId("edit_menu");
        root.getStylesheets().addAll(this.getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}