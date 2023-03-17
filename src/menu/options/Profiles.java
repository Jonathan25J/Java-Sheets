package menu.options;

import global.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import menu.edit.Profile;
import showcase.Showcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Profiles {
    @FXML
    Button profile_1;
    @FXML
    Button profile_2;
    @FXML
    Button profile_3;
    @FXML
    Button profile_4;
    @FXML
    Text message;
    public static Profile profile;
    public static ArrayList<Card> shuffledCards;
    public void initialize() {
        ArrayList<String> names = readFile();
        profile_1.setText(names.get(0));
        profile_2.setText(names.get(1));
        profile_3.setText(names.get(2));
        profile_4.setText(names.get(3));

    }

    public void p1() throws IOException {
        choice(1);
    }
    public void p2() throws IOException {
        choice(2);
    }
    public void p3() throws IOException {
        choice(3);
    }
    public void p4() throws IOException {
        choice(4);
    }

    public void choice(int number) throws IOException {
        String profile_name = readFile().get(number -1);
        profile = new Profile(profile_name, number);
        if (profile.getCards() != null) {
            shuffledCards = profile.getCards();
            Collections.shuffle(shuffledCards);

            Showcase.count = 0;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../showcase/fxml/showcase.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            root.setStyle("-fx-background-color: lightblue");
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(profile.getName());
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } else {
            message.setText(profile.getName() +" doesn't contain any cards");
        }
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\menu\\edit\\txt\\config.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}