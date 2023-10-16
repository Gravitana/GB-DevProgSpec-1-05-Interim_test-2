package view;

import model.Toy;

import java.util.List;

public interface View {

    void showInstruction();

    void showAllToys(List<Toy> toys);

    void showToyRaffle(Toy toy);

    void showRaffleQueue(List<Integer> raffle);

    void showError(Exception e);

    void showMessage(String message);

    void showInput(String message);

}
