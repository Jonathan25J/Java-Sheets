package menu.edit;

import global.Card;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public ArrayList<Card> getCards() throws IOException, ClassNotFoundException {
        InputStream is  = Files.newInputStream((Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\cards_" + number + ".txt")));
        if (is.available() > 0) {
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();
            if (obj != null) {
                cards = (ArrayList<Card>) obj;
                ois.close();
                return cards;
            } else {
                ois.close();
                return null;
            }
        }
        return null;
    }

    public void addCard(Card card) throws IOException, ClassNotFoundException {
        getCards();
        if (cards.contains(card)) {
            return;
        }
        cards.add(card);
        writeFile(cards);

    }

    public void removeCard(Card card) throws IOException, ClassNotFoundException {
        getCards();
        cards.remove(card);
        writeFile(cards);
    }
    private void writeFile(ArrayList<Card> cards) throws IOException {
        OutputStream out = Files.newOutputStream(Path.of(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\JonaqhanJars\\JavaSheets\\cards_" + number + ".txt"));
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(cards);
        oos.close();
    }
}
