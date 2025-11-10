public interface Order {
    void addItem(MenuItem item);
    void removeItem(String itemName);
    double calculateTotal();
    void displayOrder();
    void clearOrder();
    boolean isEmpty();
    int getItemCount();
}