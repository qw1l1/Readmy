import java.util.ArrayList;
import java.util.List;

public class CafeOrder implements Order {
    private List<MenuItem> items;

    public CafeOrder() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Блюдо не может быть null");
        }
        items.add(item);
        System.out.println("Добавлено: " + item.getName());
    }

    @Override
    public void removeItem(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название блюда не может быть пустым");
        }

        boolean removed = items.removeIf(item ->
                item.getName().equalsIgnoreCase(itemName.trim()));

        if (removed) {
            System.out.println("Удалено: " + itemName);
        } else {
            throw new IllegalArgumentException("Блюдо '" + itemName + "' не найдено в заказе");
        }
    }

    @Override
    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    @Override
    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("\nВаш заказ пуст");
            return;
        }

        System.out.println("Чек:");
        for (MenuItem item : items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
        System.out.println("Всего: " + calculateTotal());
    }

    @Override
    public void clearOrder() {
        items.clear();
        System.out.println("Заказ очищен");
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}