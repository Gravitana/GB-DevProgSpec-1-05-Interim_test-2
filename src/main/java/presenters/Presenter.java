package presenters;

import exceptions.EmptyStoreException;
import model.Toy;
import service.StoreService;
import view.View;

public class Presenter {
    private final View view;
    private final StoreService storeService;

    public Presenter(View view) {
        this.view = view;
        this.storeService = new StoreService();
    }

    public void start() {
        view.showAllToys(storeService.getToys());

        storeService.addToy(new Toy("Конструктор", 20, 2));
        storeService.addToy(new Toy("Робот", 20, 2));
        storeService.addToy(new Toy("Кукла", 6, 6));

        view.showAllToys(storeService.getToys());

        for (int i = 0; i < 10; i++) {
            try {
                view.showToyRaffle(storeService.raffleToy());
            } catch (EmptyStoreException e) {
                view.showError(e);
                break;
            }
        }

        view.showAllToys(storeService.getToys());
    }

}
