public interface OrderProcessor {
    void calculateTotal(double price, int quantity);
    void placeOrder(String custName, String address);
}