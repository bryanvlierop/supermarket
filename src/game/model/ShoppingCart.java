package game.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> products;

    public ShoppingCart() {
        products = new HashMap<>();
    }

    public void addProduct(Product p) {
        int amount = products.getOrDefault(p, 0);
        products.put(p, ++amount);
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Map<Product, Integer> getShoppingList() {
        return products;
    }
}
