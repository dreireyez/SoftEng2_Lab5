public class OrderTest {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new BasicOrderProcessor();
        orderProcessor.calculateTotal(10.0, 2);
        orderProcessor.placeOrder("John Doe", "123 Main St");

        // OPTIONAL: Invoice Generation
        InvoiceService invoiceGenerator = new InvoiceGenerator();
        invoiceGenerator.generateInvoice("order_123.pdf"); 

        // OPTIONAL: Email Notification
        NotificationService emailService = new EmailNotification();
        emailService.sendEmailNotification("johndoe@example.com");
    }
}