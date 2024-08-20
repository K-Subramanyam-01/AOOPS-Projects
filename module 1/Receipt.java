// Receipt class
public class Receipt {
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
