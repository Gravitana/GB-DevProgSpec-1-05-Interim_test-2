package service;

import exceptions.EmptyStoreException;
import model.Toy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    private void saveToy(Toy toy, String filename) {
        try(FileWriter writer = new FileWriter(filename, true)) {
            writer.write(toy.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }
    }
    public boolean save(String filename, String data) {
        try(FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл");
        }
    }
}
