package menu.edit;

import global.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Edit {
    @FXML
    private Menu default_;
    @FXML
    private MenuButton options;
    @FXML
    private TextField namefield;
    @FXML
    private Button OK;
    @FXML
    private Button Select;
    @FXML
    private ChoiceBox cards;
    @FXML
    private TextField question;
    @FXML
    private MenuButton card_options;
    @FXML
    private TextArea answer;
    @FXML
    private TextField qlink;
    @FXML
    private TextField alink;
    @FXML
    private Text message;
    @FXML
    private Button add;
    @FXML
    private MenuItem one;
    @FXML
    private MenuItem two;
    @FXML
    private MenuItem three;
    @FXML
    private MenuItem four;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem clear;
    private ArrayList<Profile> profiles = new ArrayList<Profile>();
    private ArrayList<MenuItem> items = new ArrayList<MenuItem>(Arrays.asList(one, two, three, four));
    private static Profile selection;
    public static Card selectedCard;

    public void initialize() {
        try {
            ifDirectoryExists("JonaqhanJars");
            ifDirectoryExists("JonaqhanJars\\JavaSheets");
            File myObj = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\config.txt");
            if (myObj.createNewFile()) {
                for (int i = 1; i < 5; i++) {
                    File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\cards_" + i + ".txt");
                    file.createNewFile();
                }
                profiles.add(new Profile("Profile: 1", 1));
                profiles.add(new Profile("Profile: 2", 2));
                profiles.add(new Profile("Profile: 3", 3));
                profiles.add(new Profile("Profile: 4", 4));
                ArrayList<String> numbers = new ArrayList<String>();
                for (Profile profile : profiles) {
                    numbers.add(profile.getName());
                }
                writeFile(numbers);
                selection = profiles.get(0);
            } else {
                int i = 1;
                for (String line : readFile()) {
                    profiles.add(new Profile(line, i));
                    i++;
                }
                for (Profile profile : profiles) {
                    profile.getCards();
                }
            }
            selection = profiles.get(0);
            read();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void read() throws IOException, ClassNotFoundException {
        cards.setValue("Cards");
        default_.setText(selection.getName());
        namefield.setText(selection.getName());
        one.setText(profiles.get(0).getName());
        two.setText(profiles.get(1).getName());
        three.setText(profiles.get(2).getName());
        four.setText(profiles.get(3).getName());
        options.setText(selection.getName());
        ObservableList<String> s_cards = FXCollections.observableArrayList();
        if (selection.getCards() != null) {
            for (Card card : selection.getCards()) {
                s_cards.add(card.getQuestion());
            }
            cards.setItems(s_cards);
        } else {
            cards.getItems().clear();
        }

    }

    public void ok() throws IOException, ClassNotFoundException {
        ArrayList<String> data = readFile();
        data.set(selection.getNumber() - 1, namefield.getText());
        selection.setName(namefield.getText());
        writeFile(data);
        read();
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\config.txt");
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


    public void writeFile(ArrayList<String> lines) throws IOException {
        FileWriter myWriter = new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\config.txt");
        int i = 1;
        for (String line : lines) {
            if (i != profiles.size()) {
                myWriter.write(line + "\n");
            } else {
                myWriter.write(line);
            }
            i++;
        }
        myWriter.close();

    }

    public void selected(Profile profile) throws IOException, ClassNotFoundException {
        selection = profile;
        read();
    }

    public void one() throws IOException, ClassNotFoundException {
        selected(profiles.get(0));
    }

    public void two() throws IOException, ClassNotFoundException {
        selected(profiles.get(1));
    }

    public void three() throws IOException, ClassNotFoundException {
        selected(profiles.get(2));
    }

    public void four() throws IOException, ClassNotFoundException {
        selected(profiles.get(3));
    }

    public void add() throws IOException, ClassNotFoundException {
        if (question.getText().isEmpty() || answer.getText().isEmpty() && alink.getText().isEmpty()) {
            message.setText("Content is required");
        } else {
            Card card = null;
            if (qlink.getText().isEmpty() && alink.getText().isEmpty() ) {
                card = new Card(question.getText(), answer.getText().replace("\n", "``"), "null", "null");
            } else if (qlink.getText().isEmpty()) {
                card = new Card(question.getText(), answer.getText().replace("\n", "``"), "null", alink.getText());
            } else if (alink.getText().isEmpty()) {
                card = new Card(question.getText(), answer.getText().replace("\n", "``"), qlink.getText(), "null");
            } else {
                card = new Card(question.getText(), answer.getText().replace("\n", "``"), qlink.getText(), alink.getText());
            }
            selection.addCard(card);
            reset("Card is added");

        }
    }

    public void cardConfirm() throws IOException, ClassNotFoundException {
        for (Card card : selection.getCards()) {
            if (card.getQuestion().equals(cards.getValue())) {
                selectedCard = card;
                question.setText(card.getQuestion());
                answer.setText(card.getAnswer().replace("``", "\n"));
                qlink.setText(card.getQlink());
                alink.setText(card.getAlink());
            }
        }

    }

    public void delete() throws IOException, ClassNotFoundException {
        if (selectedCard == null || cards.getValue() == null || question.getText().isEmpty()) return;
        selection.removeCard(selectedCard);
        reset("Card is removed");
    }

    public void reset(String notification) throws IOException, ClassNotFoundException {
        message.setText(notification);
        question.clear();
        answer.clear();
        alink.clear();
        qlink.clear();
        read();
    }

    public void save() throws IOException, ClassNotFoundException {
        if (selectedCard == null || cards.getValue() == null || question.getText().isEmpty()) return;
        selection.removeCard(selectedCard);
        add();
    }

    public void clear() throws IOException, ClassNotFoundException {
        reset("Cleared");
    }

    public void ifDirectoryExists(String name) {
        File theDir = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + name);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

}

