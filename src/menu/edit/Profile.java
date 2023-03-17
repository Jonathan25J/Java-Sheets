package menu.edit;

import global.Card;
import javafx.scene.control.MenuItem;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Profile {
    private String name;
    private int number;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Profile(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Card> getCards() {
        cards.clear();
        File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JavaSheets\\cards_" + number + ".txt");
        if (file.length() > 0) {
            ArrayList<String> s_cards = readFile();
            for (String card : s_cards) {
                List<String> data = List.of(card.split("<>"));
                cards.add(new Card(data.get(0), data.get(1), data.get(2)));
            }
            return cards;
        } else {
            return null;
        }
    }

    public void addCard(Card card) throws IOException {
        ArrayList<String> s_cards = readFile();
        if (!s_cards.isEmpty()) {
            for (Card card_ : getCards()) {
                if (card_.getQuestion().equals(card.getQuestion()) && card_.getAnswer().equals(card.getAnswer()) && card_.getLink().equals(card.getLink())) {
                    return;
                }
            }
        }
        s_cards.add(card.toString());
        cards.add(card);
        writeFile(s_cards);

    }

    public void removeCard(Card card) throws IOException {
        ArrayList<String> s_cards = readFile();
        s_cards.remove(card.toString());
        writeFile(s_cards);
        cards.remove(card);
    }

    private ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JavaSheets\\cards_" + number + ".txt");
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

    private void writeFile(ArrayList<String> lines) throws IOException {
        FileWriter myWriter = new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JavaSheets\\cards_" + number + ".txt");
        int i = 1;
        for (String line : lines) {
            if (i != cards.size()) {
                myWriter.write(line + "\n");
            } else {
                myWriter.write(line);
            }
            i++;
        }
        myWriter.close();

    }

}
