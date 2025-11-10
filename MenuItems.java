public class MenuItems {
    private String name;
    private double price;
    private String category;

    public MenuItems(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %.2f руб.", name, category, price);
    }
}