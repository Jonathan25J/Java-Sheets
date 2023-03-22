package showcase;

import global.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    private ImageView qimage;
    @FXML
    private ImageView aimage;
    @FXML
    private Button next;
    @FXML
    private Button back;

    private final Profile profile = Profiles.profile;
    private ArrayList<Card> cards = Profiles.shuffledCards;
    public static int count;

    public void initialize() {
        if (count == 0) {
            count = 1;
        } else if (count - 1 == cards.size()) {
            count -= 1;
        }
        Card card = cards.get(count - 1);
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer().replace("``", "\n"));
        answer.setVisible(false);
        qimage.setImage(null);
        aimage.setImage(null);

        if (!card.getQlink().equals("null")) {
            try {
                qimage.setImage(new Image(card.getQlink()));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        if (!card.getAlink().equals("null")) {
            try {
                aimage.setImage(new Image(card.getAlink()));
                aimage.setVisible(false);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

    }

    public void show() {
        answer.setVisible(true);
        aimage.setVisible(true);
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
