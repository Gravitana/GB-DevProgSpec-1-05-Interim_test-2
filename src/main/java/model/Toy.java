package model;

public class Toy {

    private static int nextId = 1;
    private int id;
    private String name;
    private int quantity;
    private int frequency;

    public Toy(String name, int quantity, int frequency) {
        this.id = nextId++;
        this.name = name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return id + ": " + name + " / " + frequency + "\n";
    }
}
