import java.util.Map;

// POS System class
public class PointOfSale {
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
