# Laboratory Assignment 5
This is Laboratory Assignment 5 for the course, Software Engineering 2.

## Problem Statement
The initial codebase contained a classic example of the "God Object" anti-pattern and a bloated interface. The system was centralized around a single **Order** interface and its implementation, **OrderAction**.

```
public interface Order {

  void calculateTotal(double price, int quantity);

  void placeOrder(String customerName, String address);

  void generateInvoice(String fileName);

  void sendEmailNotification(String email);
}

public class OrderAction implements Order {

  @Override
  public void calculateTotal(double price, int quantity) {
    double total = price * quantity;
    System.out.println("Order total: $" + total);
  }

  @Override
  public void placeOrder(String customerName, String address) {
    // Simulate placing order in a system
    System.out.println("Order placed for " + customerName + " at " + address);
  }

  @Override
  public void generateInvoice(String fileName) {
    // Simulate generating invoice file
    System.out.println("Invoice generated: " + fileName);
  }

  @Override
  public void sendEmailNotification(String email) {
    // Simulate sending email notification
    System.out.println("Email notification sent to: " + email);
  }
}

public class OrderTest {

  public static void main(String[] args) {
    Order order = new OrderAction();
    order.calculateTotal(10.0, 2);
    order.placeOrder("John Doe", "123 Main St");

    // These methods might not be needed for all orders
    order.generateInvoice("order_123.pdf");
    order.sendEmailNotification("johndoe@example.com");
  }
}
```

## Refactoring
To resolve these issues, the monolithic architecture was decoupled into highly cohesive, specialized components. Here is how the refactored code implements the SOLID principles:

1. **Single Responsibility Principle (SRP)**

  The responsibilities of OrderAction were extracted into three distinct classes, each with a single reason to change:

    - StandardOrderProcessor: Only handles core business logic (calculating totals and placing the order).

    - PdfInvoiceGenerator: Only handles the file I/O operations for creating an invoice.

    - EmailNotificationService: Only handles communication logistics for dispatching emails.

2. **Interface Segregation Principle (ISP)**

  The fat Order interface was divided into three smaller, role-specific interfaces:

    - OrderProcessor

    - InvoiceGenerator

    - NotificationService

  Now, clients only implement and depend on the exact functionality they need. An order that doesn't require a receipt simply skips interacting with the InvoiceGenerator.

3. **Open/Closed Principle (OCP)**
  
  The system is now open for extension but closed for modification.

  If we need to add a new notification type (e.g., SMS), we simply create a new class SmsNotificationService that implements NotificationService.

  The StandardOrderProcessor remains completely untouched, ensuring core order mechanics are never jeopardized by feature additions.

4. **Dependency Inversion Principle (DIP)**

  High-level modules (like the main method in OrderTest) no longer depend on a massive, all-knowing concrete class. Instead, the logic relies on abstractions (OrderProcessor, InvoiceGenerator, NotificationService). This makes the system highly modular and makes it easy to mock these services for unit testing, or inject different implementations dynamically at runtime.