package showcase;

import global.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import menu.edit.Profile;
import menu.options.Profiles;


import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;

public class Showcase {
    @FXML
    private Text question;
    @FXML
    private Button show;
    @FXML
    private TextArea answer;
    @FXML
    private ImageView image;
    @FXML
    private Button next;
    @FXML
    private Button back;

    private final Profile profile = Profiles.profile;
    private ArrayList<Card> cards = Profiles.shuffledCards;
    public static int count;

    public void initialize() throws IOException {
        if (count == 0) {
            count = 1;
        } else if (count - 1 == cards.size()) {
            count -= 1;
        }
        Card card = cards.get(count - 1);
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer().replace("``", "\n"));
        answer.setVisible(false);
        image.setImage(null);


        if (!card.getLink().equals("null")) {
            try {
                image.setImage(new Image(card.getLink()));
                image.setVisible(false);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

    }

    public void show() {
        answer.setVisible(true);
        image.setVisible(true);
    }

    public void next() throws IOException {
        count++;
        initialize();

    }

    public void back() throws IOException {
        count -= 1;
        initialize();

    }
}
