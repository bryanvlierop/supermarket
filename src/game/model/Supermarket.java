package game.model;

import game.controller.CashRegister;

import java.util.HashMap;
import java.util.Map;

public class Supermarket {
    private final CashRegister cashRegister;
    private final Map<Product, Integer> productStock;

    public Supermarket() {
        cashRegister = new CashRegister();
        productStock = new HashMap<>();

        productStock.put(new Product("Soap", 3.00), 100);
        productStock.put(new Product("Cereals", 2.50), 50);
        productStock.put(new Product("Chinese vegetables", 5.00), 20);
        productStock.put(new Product("Yoghourt", 2.00), 0);
        productStock.put(new Product("Diapers", 10.00), 5);
    }

    public Product getProductByName(String product) {
        return productStock.keySet().stream()
                .filter(p -> product.equalsIgnoreCase(p.getName()))
                .findFirst()
                .orElse(null);
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
}
