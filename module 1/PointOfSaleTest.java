import java.util.HashMap;
import java.util.Map;

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
    }
}
