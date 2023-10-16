import presenters.Presenter;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new Presenter(new ConsoleView());
        presenter.start();

    }
}
