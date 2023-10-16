package service;

import exceptions.EmptyStoreException;
import model.Toy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreService {

    private final List<Toy> toys = new ArrayList<>();
    private List<Integer> raffle = new ArrayList<>();
    private String filename = "src/main/resources/toys.txt";

    public List<Toy> getToys() {
        return this.toys;
    }

    public List<Integer> getRafle() {
        return this.raffle;
    }

    public void addToy(Toy toy) {
        this.toys.add(toy);
        for (int i = 0; i < toy.getFrequency(); i++) {
            this.raffle.add(toy.getId());
        }
        Collections.shuffle(this.raffle);
    }

    public Toy getRandomToy() {
        int max = this.raffle.size();
        int index = (int) (Math.random() * max);
        int toyId = this.raffle.get(index);
        for (Toy t: this.toys) {
            if (toyId == t.getId()) {
                return t;
            }
        }
        return null;
    }

    public void removeToy(Toy toy, int quantity) throws EmptyStoreException {
        for (Toy currentToy: this.toys) {
            if (toy.getId() == currentToy.getId()) {
                int delta = currentToy.getQuantity() - quantity;
                if (delta < 0) {
                    throw new EmptyStoreException("Не хватает игрушек для розыгрыша");
                } else {
                    currentToy.setQuantity(delta);
                }
                break;
            }
        }
    }

    public Toy raffleToy() throws EmptyStoreException {
        Toy toy = getRandomToy();
        removeToy(toy, 1);
        saveToy(toy, this.filename);
        return toy;
    }

    public void createToy(String userInput) {
        String[] data = userInput.split(" ");
        Toy toy = new Toy(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        addToy(toy);
    }

    public boolean changeFrequency(String userInput) {
        String[] data = userInput.split(" ");
        int toyId = Integer.parseInt(data[0]);
        int frequency = Integer.parseInt(data[1]);
        for (Toy t: this.toys) {
            if (toyId == t.getId()) {
                t.setFrequency(frequency);
                return true;
            }
        }
        return false;
    }

    private void saveToy(Toy toy, String filename) {
        try(FileWriter writer = new FileWriter(filename, true)) {
            writer.write(toy.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }
    }
}
