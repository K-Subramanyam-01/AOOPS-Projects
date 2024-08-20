import java.util.ArrayList;
import java.util.List;

// Subject: ChatRoom
class ChatRoom {
    private List<Observer> users = new ArrayList<>();
    private String message;

    public void join(Observer user) {
        users.add(user);
    }

    public void leave(Observer user) {
        users.remove(user);
    }

    public void sendMessage(String message) {
        this.message = message;
        notifyAllUsers();
    }

    private void notifyAllUsers() {
        for (Observer user : users) {
            user.update(message);
        }
    }
}

// Observer: User
interface Observer {
    void update(String message);
}

// Concrete Observer: UserImpl
class UserImpl implements Observer {
    private String name;
    private String receivedMessage;

    public UserImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.receivedMessage = message;
        System.out.println(name + " received: " + message);
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }
}

// Test the Chat Application
public class ChatAppTest {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        UserImpl user1 = new UserImpl("User 1");
        UserImpl user2 = new UserImpl("User 2");

        chatRoom.join(user1);
        chatRoom.join(user2);

        chatRoom.sendMessage("Hello, everyone!");

        assert user1.getReceivedMessage().equals("Hello, everyone!") : "User 1 did not receive the correct message!";
        assert user2.getReceivedMessage().equals("Hello, everyone!") : "User 2 did not receive the correct message!";

        System.out.println("All tests passed!");
    }
}
import java.util.HashMap;
import java.util.Map;

// Product class
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Inventory class
class Inventory {
    private Map<Product, Integer> stock = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public boolean isAvailable(Product product, int quantity) {
        return stock.getOrDefault(product, 0) >= quantity;
    }

    public void reduceStock(Product product, int quantity) {
        if (isAvailable(product, quantity)) {
            stock.put(product, stock.get(product) - quantity);
        }
    }
}

// Receipt class
class Receipt {
    private StringBuilder details = new StringBuilder();
    private double total;

    public void addItem(Product product, int quantity) {
        double itemTotal = product.getPrice() * quantity;
        details.append(product.getName()).append(" x ").append(quantity)
                .append(": $").append(itemTotal).append("\n");
        total += itemTotal;
    }

    public String generateReceipt() {
        details.append("Total: $").append(total);
        return details.toString();
    }
}

// POS System class
class PointOfSale {
    private Inventory inventory;

    public PointOfSale(Inventory inventory) {
        this.inventory = inventory;
    }

    public String processTransaction(Map<Product, Integer> cart) {
        Receipt receipt = new Receipt();

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (inventory.isAvailable(product, quantity)) {
                inventory.reduceStock(product, quantity);
                receipt.addItem(product, quantity);
            } else {
                return "Transaction failed: Insufficient stock for " + product.getName();
            }
        }

        return receipt.generateReceipt();
    }
}

// Test the POS System
public class PointOfSaleTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product apple = new Product("Apple", 0.5);
        Product banana = new Product("Banana", 0.3);

        inventory.addProduct(apple, 10);
        inventory.addProduct(banana, 5);

        PointOfSale pos = new PointOfSale(inventory);

        Map<Product, Integer> cart = new HashMap<>();
        cart.put(apple, 2);
        cart.put(banana, 3);

        String receipt = pos.processTransaction(cart);

        System.out.println(receipt);

        assert inventory.isAvailable(apple, 8) : "Apple stock should be 8";
        assert inventory.isAvailable(banana, 2) : "Banana stock should be 2";

        System.out.println("All tests passed!");
    }
}
