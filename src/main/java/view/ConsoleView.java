package view;

import model.Toy;

import java.util.List;

public class ConsoleView implements View {
    @Override
    public void showAllToys(List<Toy> toys) {
        showUpLine();
        if (toys.isEmpty()) {
            showMessage("Список игрушек пуст");
        } else {
            System.out.print( "║  id │ Наименование     │Кол-во│Частота║\n");
            System.out.print( "╟─────┼──────────────────┼──────┼───────╢\n");
            for (Toy toy : toys) {
                showRow(toy);
            }
        }
        showBottomLine();
    }

    @Override
    public void showToyRaffle(Toy toy) {
        System.out.println( "║ " + toy.getName());
    }

    @Override
    public void showRaffleQueue(List<Integer> raffle) {
        System.out.println(raffle);
    }

    @Override
    public void showError(Exception e) {
        System.out.println("╔════════╤══════════════════════════════════════╗");
        System.out.printf( "║ ОШИБКА │ %-36s ║\n", e.getMessage());
        System.out.println("╚════════╧══════════════════════════════════════╝");
    }

    private void showUpLine() {
        System.out.println("╔═══════════════════════════════════════╗");
    }
    private void showRow(Toy toy) {
        System.out.printf( "║ %3d │ %-16s │ %4d │  %3d  ║\n", toy.getId(), toy.getName(), toy.getQuantity(), toy.getFrequency());
    }
    private void showMessage(String message) {
        System.out.printf( "║ %-37s ║\n", message);
    }
    private void showBottomLine() {
        System.out.println("╚═══════════════════════════════════════╝");
    }
}
