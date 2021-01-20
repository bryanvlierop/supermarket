package game.model;

import java.util.HashMap;
import java.util.Map;

public class Supermarket {
    private final CashRegister cashRegister;
    private final Map<Product, Integer> productStock;

    public Supermarket() {
        cashRegister = new CashRegister();
        productStock = new HashMap<>();
    }

    public Product getProductByName(String product) {
        return productStock.keySet().stream()
                .filter(p -> product.equalsIgnoreCase(p.getName()))
                .findFirst()
                .orElse(null);
    }

    public void decreaseStock(Product p) {
        productStock.put(p, productStock.getOrDefault(p, 1) - 1);
    }

    public boolean hasStock(Product p) {
        return productStock.getOrDefault(p, 0) > 0;
    }

    public boolean buyProduct(Product p, int amount) {
        int available = productStock.getOrDefault(p, 0);
        if ((available - amount) < 0)
            return false;
        productStock.replace(p, available - amount);
        return true;
    }

    public void addStock(Product p, int amount) {
        int currentStock = productStock.getOrDefault(p, 0);
        productStock.put(p, currentStock + amount);
    }

    public CashRegister getRegister() {
        return cashRegister;
    }
}
