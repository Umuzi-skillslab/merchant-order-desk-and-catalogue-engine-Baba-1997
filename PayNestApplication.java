public class PayNestApplication {

    public static void main(String[] args) {

        Product laptop = new Product(1, "Laptop", 12000);
        Product mouse = new Product(2, "Wireless Mouse", 350);

        Customer customer =
                new Customer(1, "Chuma Gqodi", "chuma.gqodi@umuzi.org");

        Order order = new Order(101, customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        order.printSummary();
    }
}