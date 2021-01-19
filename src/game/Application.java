package game;

import game.controller.SupermarketController;
import game.model.CashRegister;
import game.model.Product;
import game.model.ShoppingCart;
import game.model.Supermarket;

public class Application {

    public static void main(String[] args) {
        SupermarketController market = new SupermarketController();

        market.supply(new Product("Soap", 3.00), 100);
        market.supply(new Product("Cereals", 2.50), 50);
        market.supply(new Product("Chinese vegetables", 5.00), 20);
        market.supply(new Product("Yoghourt", 2.00), 0);
        market.supply(new Product("Diapers", 10.00), 5);

        var shoppingCart = new ShoppingCart();

        //aan het winkelen
        shoppingCart.addProduct(market.getProductByName("Soap"));

        market.checkout(shoppingCart);
    }
}
