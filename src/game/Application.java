package game;

import game.controller.CashRegister;
import game.model.Product;
import game.model.ShoppingCart;
import game.model.Supermarket;

public class Application {

    public static void main(String[] args) {
        Supermarket market = new Supermarket();

        //supply market
        market.addStock(new Product("Soap", 3.00), 100);
        market.addStock(new Product("Cereals", 2.50), 50);
        market.addStock(new Product("Chinese vegetables", 5.00), 20);
        market.addStock(new Product("Yoghourt", 2.00), 0);
        market.addStock(new Product("Diapers", 10.00), 5);

        var register = new CashRegister();
        var shoppingCart = new ShoppingCart();

        //aan het winkelen
        shoppingCart.addProduct(market.getProductByName("Soap"));

        register.checkout(shoppingCart);
    }
}
