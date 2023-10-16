package view;

import model.Toy;

import java.util.List;

public interface View {

    void showAllToys(List<Toy> toys);

    void showToyRaffle(Toy toy);

    void showRaffleQueue(List<Integer> raffle);

    void showError(Exception e);
}
