package presenters;

import exceptions.EmptyStoreException;
import model.Toy;
import service.StoreService;
import view.View;

import java.util.Scanner;

public class Presenter {
    private final View view;
    private final StoreService storeService;

    public Presenter(View view) {
        this.view = view;
        this.storeService = new StoreService();
    }

    public void start() {
        storeService.addToy(new Toy("Конструктор", 20, 2));
        storeService.addToy(new Toy("Робот", 20, 2));
        storeService.addToy(new Toy("Кукла", 6, 6));

        view.showAllToys(storeService.getToys());

        while (true) {
            view.showInstruction();

            String userInput = getUserInput("Введите команду: ");

            if (userInput.equals("0")) {
                break;
            } else if (userInput.equals("1")) {
                addToy();
            } else if (userInput.equals("2")) {
                changeFrequency();
            } else if (userInput.equals("3")) {
                startRaffle();
                break;
            }
        }

        view.showMessage("Лотерея окончена");
    }

    private String getUserInput(String message) {
        Scanner in = new Scanner(System.in);
        String result;
        do {
            view.showInput(message);
            result = in.nextLine();
        } while (result.isEmpty());

        return result;
    }

    private void addToy() {
        String userInput = getUserInput("Введите название, количество и вес (одной строкой через пробел, 0 - отмена): ");
        if (!userInput.equals("0")) {
            storeService.createToy(userInput);
        }
        view.showAllToys(storeService.getToys());
    }

    private void startRaffle() {
        while (true) {
            String userInput = getUserInput("Желаете получить игрушку в подарок? (y/n): ");

            if (userInput.equalsIgnoreCase("n")) {
                break;
            }
            try {
                view.showToyRaffle(storeService.raffleToy());
            } catch (EmptyStoreException e) {
                view.showError(e);
                break;
            }
        }
        view.showAllToys(storeService.getToys());
    }

    private void changeFrequency() {
        view.showAllToys(storeService.getToys());
        String userInput = getUserInput("Введите id игрушки и новый вес (одной строкой через пробел, 0 - отмена): ");
        if (!userInput.equals("0")) {
            if (!storeService.changeFrequency(userInput)) {
                view.showMessage("Игрушка не найдена");
            }
        }
        view.showAllToys(storeService.getToys());
    }
}
