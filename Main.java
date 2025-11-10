import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Menu menu;
    private CafeOrder currentOrder;
    private Scanner scanner;

    public Main() {
        this.menu = new Menu();
        this.currentOrder = new CafeOrder();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в систему заказов кафе!");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1 -> showMenu();
                case 2 -> addItemToOrder();
                case 3 -> removeItemFromOrder();
                case 4 -> viewCurrentOrder();
                case 5 -> checkout();
                case 6 -> {
                    running = false;
                    System.out.println("Спасибо за посещение! До свидания!");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\nГлавное меню");
        System.out.println("1.Показать меню");
        System.out.println("2.Добавить блюдо в заказ");
        System.out.println("3.Удалить блюдо из заказа");
        System.out.println("4.Просмотреть текущий заказ");
        System.out.println("5.Оформить заказ");
        System.out.println("6.Выйти");
    }

    private void showMenu() {
        menu.displayMenu();
    }

    private void addItemToOrder() {
        System.out.print("\nВведите название блюда для добавления: ");
        String itemName = scanner.nextLine();

        try {
            MenuItem item = menu.findMenuItem(itemName);
            if (item == null) {
                System.out.println("Блюдо '" + itemName + "' не найдено в меню.");
                return;
            }

            currentOrder.addItem(item);

        } catch (Exception e) {
            System.out.println("Ошибка при добавлении блюда: " + e.getMessage());
        }
    }

    private void removeItemFromOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Ваш заказ пуст. Нечего удалять.");
            return;
        }

        System.out.print("Введите название блюда для удаления: ");
        String itemName = scanner.nextLine();

        try {
            currentOrder.removeItem(itemName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка при удалении: " + e.getMessage());
        }
    }

    private void viewCurrentOrder() {
        currentOrder.displayOrder();
    }

    private void checkout() {
        if (currentOrder.isEmpty()) {
            System.out.println("Ваш заказ пуст. Добавьте блюда перед оформлением.");
            return;
        }

        System.out.println("\nОформление заказа!");
        currentOrder.displayOrder();

        System.out.print("\nПодтвердить заказ? (Да/Нет): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Да")) {
            System.out.println("\nЗаказ успешно оформлен! Приятного аппетита!");
            System.out.println("Примерное время приготовления: " +
                    (currentOrder.getItemCount() * 5) + " минут");
            currentOrder.clearOrder();
        } else {
            System.out.println("Оформление заказа отменено.");
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите число.");
                scanner.nextLine();
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Main system = new Main();
        system.start();
    }
}