import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menu;

    public Menu() {
        this.menu = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        menu.add(new MenuItem("Стейк Рибай", 1200.0, "Основное"));
        menu.add(new MenuItem("Кофе Латте", 250.0, "Напитки"));
        menu.add(new MenuItem("Тирамису", 350.0, "Десерт"));
        menu.add(new MenuItem("Картофель фри", 180.0, "Закуска"));
    }

    public void displayMenu() {
        System.out.println("\nМеню");
        System.out.printf("Блюдо ", "Категория", "Цена");

        String currentCategory = "";
        for (MenuItem item : menu) {
            if (!item.getCategory().equals(currentCategory)) {
                currentCategory = item.getCategory();
                System.out.println("\n【 " + currentCategory + " 】");
            }
            System.out.println(item);
        }
    }

    public MenuItem findMenuItem(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            return null;
        }

        return menu.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName.trim()))
                .findFirst()
                .orElse(null);
    }

    public List<MenuItem> getMenuByCategory(String category) {
        return menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}