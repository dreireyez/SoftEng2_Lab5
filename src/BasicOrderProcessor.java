public class BasicOrderProcessor implements OrderProcessor {
    @Override
    public void calculateTotal(double price, int quantity) {
        double total = price * quantity;
        System.out.println("Order total: " + String.format("$ %.2f",total));
    }

    @Override
    public void placeOrder(String custName, String address) {
        System.out.println("Order placed for " + custName + " at " + address);
    }
}